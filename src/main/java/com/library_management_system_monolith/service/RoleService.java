package com.library_management_system_monolith.service;

import com.library_management_system_monolith.entity.Role;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.payload.RoleDto;
import com.library_management_system_monolith.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ApiResponse<RoleDto> createRole(RoleDto roleDto){
        if(roleDto == null){
            throw new IllegalArgumentException("Role is null");
        }

        ApiResponse apiResponse = new ApiResponse<>();

        Role existingRole = roleRepository.findByRoleName(roleDto.getRoleName());
        if(existingRole != null){
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Role with name "+roleDto.getRoleName()+" already exists");
            apiResponse.setPayload(null);
            return apiResponse;
        }

        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        Role savedRole = roleRepository.save(role);
        RoleDto savedRoleDto = mapRoleEntityToRoleDto(savedRole);

        apiResponse.setStatus(true);
        apiResponse.getMessages().add("Role with name "+savedRole.getRoleName()+" successfully created");
        apiResponse.setPayload(savedRoleDto);
        return apiResponse;
    }

    RoleDto mapRoleEntityToRoleDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
