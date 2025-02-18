package com.library_management_system_monolith.controller;

import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.payload.RoleDto;
import com.library_management_system_monolith.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@RequestBody RoleDto roleDto){
        ApiResponse<RoleDto> savedRole = roleService.createRole(roleDto);
        if(savedRole.getPayload() != null){
            return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(savedRole, HttpStatus.BAD_REQUEST);
        }
    }
}
