package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
}
