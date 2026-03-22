package ru.itche.backend.repository.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.itche.backend.entity.auth.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLogin(String username);

    @Modifying
    @Query(value = "delete from sporta.users where id = :id", nativeQuery = true)
    void deleteUser(@Param("id") Long id);
}
