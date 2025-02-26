package com.library_management_system_monolith.service;

import com.library_management_system_monolith.entity.Role;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.payload.RoleDto;
import com.library_management_system_monolith.repository.RoleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ApiResponse<RoleDto> createRole(RoleDto roleDto) {
        logger.info("Received request to create Role : {}", roleDto);

        if (roleDto == null) {
            logger.error("Role Dto is null");
            throw new IllegalArgumentException("Role is null");
        }

        ApiResponse<RoleDto> apiResponse = new ApiResponse<>();

        Role existingRole = roleRepository.findByRoleName(roleDto.getRoleName());
        if (existingRole != null) {
            logger.warn("Role with name {} already exists", roleDto.getRoleName());
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Role with name " + roleDto.getRoleName() + " already exists");
            apiResponse.setPayload(null);
            return apiResponse;
        }

        try {
            Role role = new Role();
            role.setRoleName(roleDto.getRoleName());
            Role savedRole = roleRepository.save(role);
            RoleDto savedRoleDto = mapRoleEntityToRoleDto(savedRole);
            logger.info("Successfully created role : {}", savedRoleDto);

            apiResponse.setStatus(true);
            apiResponse.getMessages().add("Role with name " + savedRole.getRoleName() + " successfully created");
            apiResponse.setPayload(savedRoleDto);
        } catch (Exception e) {
            logger.error("Error while creating {}", e.getMessage(), e);
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Error while creating role : " + e.getMessage());
        }

        return apiResponse;
    }

    public ApiResponse<RoleDto> getRoleById(Long roleId) {
        logger.info("Fetching role with id: {}", roleId);
        ApiResponse<RoleDto> apiResponse = new ApiResponse<>();
        Optional<Role> optionalRole = roleRepository.findById(Math.toIntExact(roleId));

        if (!optionalRole.isPresent()) {
            logger.warn("Role with id {} not found", roleId);
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Role with id " + roleId + " not found");
            return apiResponse;
        }

        Role role = optionalRole.get();
        RoleDto roleDto = mapRoleEntityToRoleDto(role);
        logger.info("Successfully retrieved role: {}", roleDto);

        apiResponse.setStatus(true);
        apiResponse.setPayload(roleDto);
        apiResponse.getMessages().add("Role retrieved successfully");
        return apiResponse;
    }

    public ApiResponse<List<RoleDto>> getAllRoles() {
        logger.info("Fetching all roles");
        ApiResponse apiResponse = new ApiResponse<>();
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            logger.warn("No roles found in the database");
            apiResponse.setStatus(false);
            apiResponse.setPayload(null);
            apiResponse.getMessages().add("No role name is found");
            return apiResponse;
        }

        List<RoleDto> roleDtos = roles
                .stream()
                .map(RoleService::mapRoleEntityToRoleDto
                ).collect(Collectors.toList());
        logger.info("Successfully retrieved {} roles", roleDtos.size());

        apiResponse.setStatus(true);
        apiResponse.setPayload(roleDtos);
        apiResponse.getMessages().add("List All Roles");
        return apiResponse;
    }

    public ApiResponse<RoleDto> updateRole(Long roleId, RoleDto roleDto) {
        logger.info("Received request to update Role with id: {}", roleId);
        ApiResponse<RoleDto> apiResponse = new ApiResponse<>();

        Role existingRole = roleRepository.findById(Math.toIntExact(roleId))
                .orElse(null);

        if (existingRole == null) {
            logger.warn("Role with id {} not found", roleId);
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Role with id " + roleId + " not found");
            return apiResponse;
        }

        try {
            existingRole.setRoleName(roleDto.getRoleName());
            Role updatedRole = roleRepository.save(existingRole);
            RoleDto updatedRoleDto = mapRoleEntityToRoleDto(updatedRole);
            logger.info("Successfully updated role: {}", updatedRoleDto);

            apiResponse.setStatus(true);
            apiResponse.getMessages().add("Role with id " + roleId + " successfully updated");
            apiResponse.setPayload(updatedRoleDto);
        } catch (Exception e) {
            logger.error("Error while updating role: {}", e.getMessage(), e);
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Failed to update role: " + e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse<String> deleteRole(Long roleId) {
        logger.info("Received request to delete Role with id: {}", roleId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        Optional<Role> optionalRole = roleRepository.findById(Math.toIntExact(roleId));

        if (!optionalRole.isPresent()) {
            logger.warn("Role with id {} not found", roleId);
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Role with id " + roleId + " not found");
            return apiResponse;
        }

        try {
            roleRepository.deleteById(Math.toIntExact(roleId));
            logger.info("Successfully deleted role with id: {}", roleId);
            apiResponse.setStatus(true);
            apiResponse.getMessages().add("Role with id " + roleId + " successfully deleted");
            apiResponse.setPayload("Role deleted successfully");
        } catch (Exception e) {
            logger.error("Error while deleting role: {}", e.getMessage(), e);
            apiResponse.setStatus(false);
            apiResponse.getMessages().add("Error while deleting role: " + e.getMessage());
        }
        return apiResponse;
    }

    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role ID %s Not Found !!", id))
        );
    }

    private static RoleDto mapRoleEntityToRoleDto(Role role) {
        logger.debug("Mapping Role entity to RoleDto: {}", role);
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
