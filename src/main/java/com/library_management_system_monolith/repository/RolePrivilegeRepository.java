package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.RolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, Integer> {

    @Query(value = """
        SELECT P.privilege_name 
        FROM PRIVILEGES P 
        JOIN ROLE_PRIVILEGES RP ON P.privilege_id = RP.privilege_id 
        WHERE RP.role_id = :roleId
    """, nativeQuery = true)
    List<String> getPrivilegesByRoleId(@Param("roleId") Integer roleId);
}
