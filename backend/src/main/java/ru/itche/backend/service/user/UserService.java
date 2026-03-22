package ru.itche.backend.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.dto.user.UpdateUserPasswordPayload;
import ru.itche.backend.dto.user.UpdateUserPayload;
import ru.itche.backend.entity.auth.Role;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.repository.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String login, String password, String email, String phone, Role role) {

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);

        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + login));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Transactional
    public void updateUser(Long id, UpdateUserPayload payload) {
        userRepository.findById(id)
                .ifPresent(user -> {

                    Optional.ofNullable(payload.login())
                            .ifPresent(user::setLogin);

                    Optional.ofNullable(payload.email())
                            .ifPresent(user::setEmail);

                    Optional.ofNullable(payload.phone())
                            .ifPresent(user::setPhone);
                });
    }

    @Transactional
    public void updateUserPassword(Long id, UpdateUserPasswordPayload payload) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        if (!passwordEncoder.matches(payload.oldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Неверный текущий пароль");
        }

        user.setPassword(passwordEncoder.encode(payload.newPassword()));
        userRepository.save(user);
    }
}
