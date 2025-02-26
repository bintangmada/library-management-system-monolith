package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName);

    @Query("SELECT r.roleName FROM Role r WHERE r.roleId = :roleId")
    String getRoleNameById(@Param("roleId") Integer roleId);
}
