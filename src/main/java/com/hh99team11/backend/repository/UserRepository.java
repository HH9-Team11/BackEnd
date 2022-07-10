package com.hh99team11.backend.repository;

import com.hh99team11.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository {
    Optional<User> findByUsername(String username);

    Optional<User> findById(long l);
}
