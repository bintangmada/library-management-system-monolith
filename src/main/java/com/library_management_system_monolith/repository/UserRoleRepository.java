package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT ur.roleId FROM UserRole ur WHERE ur.userId = :userId")
    List<Integer> getRoleIdsByUserId(@Param("userId") Integer userId);
}
