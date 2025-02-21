package com.library_management_system_monolith.controller;

import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.payload.RoleDto;
import com.library_management_system_monolith.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@RequestBody RoleDto roleDto) {
        logger.info("Received request to create role: {}", roleDto);
        ApiResponse<RoleDto> savedRole = roleService.createRole(roleDto);
        return new ResponseEntity<>(savedRole, savedRole.getPayload() != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles() {
        logger.info("Received request to fetch all roles");
        ApiResponse<List<RoleDto>> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, roles.getPayload() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse<RoleDto>> getRoleById(@PathVariable Long roleId) {
        logger.info("Received request to fetch role by id: {}", roleId);
        ApiResponse<RoleDto> roleResponse = roleService.getRoleById(roleId);
        return new ResponseEntity<>(roleResponse, roleResponse.getPayload() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponse<RoleDto>> updateRole(@PathVariable Long roleId, @RequestBody RoleDto roleDto) {
        logger.info("Received request to update role with id: {}", roleId);
        ApiResponse<RoleDto> updatedRole = roleService.updateRole(roleId, roleDto);
        return new ResponseEntity<>(updatedRole, updatedRole.getPayload() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse<String>> deleteRole(@PathVariable Long roleId) {
        logger.info("Received request to delete role with id: {}", roleId);
        ApiResponse<String> deleteResponse = roleService.deleteRole(roleId);

        if (deleteResponse.isStatus()) {
            return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(deleteResponse, HttpStatus.NOT_FOUND);
        }
    }
}
