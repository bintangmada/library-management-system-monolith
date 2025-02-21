package com.library_management_system_monolith.service;

import com.library_management_system_monolith.entity.RolePrivilege;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.repository.RolePrivilegeRepository;
import org.springframework.stereotype.Service;

@Service
public class RolePrivilegeService {

    private final RolePrivilegeRepository rolePrivilegeRepository;

    public RolePrivilegeService(RolePrivilegeRepository rolePrivilegeRepository) {
        this.rolePrivilegeRepository = rolePrivilegeRepository;
    }

    public ApiResponse<RolePrivilege> assignPrivilegeToRole(Integer roleId, Integer privilegeId) {
        ApiResponse<RolePrivilege> response = new ApiResponse<>();

        RolePrivilege rolePrivilege = new RolePrivilege();
        rolePrivilege.setRoleId(roleId);
        rolePrivilege.setPrivilegeId(privilegeId);

        RolePrivilege savedRolePrivilege = rolePrivilegeRepository.save(rolePrivilege);

        response.setStatus(true);
        response.setPayload(savedRolePrivilege);
        response.getMessages().add("Privilege assigned to role successfully");

        return response;
    }
}