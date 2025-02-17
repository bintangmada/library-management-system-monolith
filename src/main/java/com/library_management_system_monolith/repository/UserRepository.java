package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
