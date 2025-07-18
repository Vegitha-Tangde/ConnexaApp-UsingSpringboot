package com.example.Connexa.Connexa.repository;

import com.example.Connexa.Connexa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT u From User u Where u.id IN :users")
    List<User> findAllUsersByUserIds(@Param("users") List<Long> userIds);

    @Query("SELECT DISTINCT u from User u Where u.username LIKE %:query% OR u.email LIKE %:query%")
    List<User> findByQuery(@Param("query") String query);
}
