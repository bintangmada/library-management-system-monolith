package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.isActive = TRUE WHERE a.userId = ?1")
    int enableUser(int userId);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.isActive = FALSE WHERE a.userId = ?1")
    int disableUser(int userId);
}
