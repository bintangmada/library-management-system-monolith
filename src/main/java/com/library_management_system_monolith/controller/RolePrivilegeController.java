package com.library_management_system_monolith.controller;

import com.library_management_system_monolith.dto.RolePrivilegeDto;
import com.library_management_system_monolith.entity.RolePrivilege;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.service.RolePrivilegeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role-privileges")
public class RolePrivilegeController {

    private final RolePrivilegeService rolePrivilegeService;

    public RolePrivilegeController(RolePrivilegeService rolePrivilegeService) {
        this.rolePrivilegeService = rolePrivilegeService;
    }

    @PostMapping("/assign")
    public ResponseEntity<ApiResponse<RolePrivilege>> assignPrivilegeToRole(
            @RequestBody RolePrivilegeDto request) {
        return ResponseEntity.ok(rolePrivilegeService.assignPrivilegeToRole(request.getRoleId(), request.getPrivilegeId()));
    }

}
