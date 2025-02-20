package com.library_management_system_monolith.service;

import com.library_management_system_monolith.entity.Role;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.payload.RoleDto;
import com.library_management_system_monolith.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ApiResponse<List<RoleDto>> getAllRoles(){
        ApiResponse apiResponse = new ApiResponse<>();
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()){
            apiResponse.setStatus(false);
            apiResponse.setPayload(null);
            apiResponse.getMessages().add("No role name is found");
            return apiResponse;
        }

        List<RoleDto> roleDtos = roles
                .stream()
                .map(role -> {
                    return mapRoleEntityToRoleDto(role);
                }).collect(Collectors.toList());

        apiResponse.setStatus(true);
        apiResponse.setPayload(roleDtos);
        apiResponse.getMessages().add("List All Roles");
        return apiResponse;
    }

    RoleDto mapRoleEntityToRoleDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
