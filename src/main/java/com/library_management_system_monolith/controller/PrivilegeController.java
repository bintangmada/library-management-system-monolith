package com.library_management_system_monolith.controller;

import com.library_management_system_monolith.entity.Privilege;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.service.PrivilegeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Privilege>>> getAllPrivileges() {
        return ResponseEntity.ok(privilegeService.getAllPrivileges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Privilege>> getPrivilegeById(@PathVariable Integer id) {
        return ResponseEntity.ok(privilegeService.getPrivilegeById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Privilege>> createPrivilege(@RequestBody Privilege privilege) {
        return ResponseEntity.ok(privilegeService.createPrivilege(privilege));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Privilege>> updatePrivilege(@PathVariable Integer id, @RequestBody Privilege privilegeDetails) {
        return ResponseEntity.ok(privilegeService.updatePrivilege(id, privilegeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePrivilege(@PathVariable Integer id) {
        return ResponseEntity.ok(privilegeService.deletePrivilege(id));
    }
}
