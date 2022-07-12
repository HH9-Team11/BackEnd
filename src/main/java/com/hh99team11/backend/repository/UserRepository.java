package com.hh99team11.backend.repository;

import com.hh99team11.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(long l);

//    @Query("select u.id from User u where u.id =: userId and  ")
//    List<User> findNearByUser(
//            @Param("userId") long userId);
}
