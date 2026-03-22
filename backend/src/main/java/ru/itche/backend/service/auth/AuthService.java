package ru.itche.backend.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itche.backend.dto.auth.JwtRequest;
import ru.itche.backend.dto.auth.JwtResponse;
import ru.itche.backend.security.JwtTokenUtils;
import ru.itche.backend.security.UserDetailsServiceImpl;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password()));

        UserDetails userDetails = userService.loadUserByUsername(authRequest.login());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}