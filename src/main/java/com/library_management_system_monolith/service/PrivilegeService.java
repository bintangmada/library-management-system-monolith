package com.library_management_system_monolith.service;

import com.library_management_system_monolith.entity.Privilege;
import com.library_management_system_monolith.payload.ApiResponse;
import com.library_management_system_monolith.repository.PrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public ApiResponse<List<Privilege>> getAllPrivileges() {
        List<Privilege> privileges = privilegeRepository.findAll();
        ApiResponse<List<Privilege>> response = new ApiResponse<>();
        response.setStatus(true);
        response.setPayload(privileges);
        response.getMessages().add("Data retrieved successfully");
        return response;
    }

    public ApiResponse<Privilege> getPrivilegeById(Integer id) {
        ApiResponse<Privilege> response = new ApiResponse<>();
        Optional<Privilege> privilege = privilegeRepository.findById(id);
        if (privilege.isPresent()) {
            response.setStatus(true);
            response.setPayload(privilege.get());
            response.getMessages().add("Privilege found");
        } else {
            response.setStatus(false);
            response.getMessages().add("Privilege not found");
        }
        return response;
    }

    public ApiResponse<Privilege> createPrivilege(Privilege privilege) {
        Privilege savedPrivilege = privilegeRepository.save(privilege);
        ApiResponse<Privilege> response = new ApiResponse<>();
        response.setStatus(true);
        response.setPayload(savedPrivilege);
        response.getMessages().add("Privilege created successfully");
        return response;
    }

    public ApiResponse<Privilege> updatePrivilege(Integer id, Privilege privilegeDetails) {
        ApiResponse<Privilege> response = new ApiResponse<>();
        return privilegeRepository.findById(id)
                .map(privilege -> {
                    privilege.setPrivilegeName(privilegeDetails.getPrivilegeName());
                    Privilege updatedPrivilege = privilegeRepository.save(privilege);
                    response.setStatus(true);
                    response.setPayload(updatedPrivilege);
                    response.getMessages().add("Privilege updated successfully");
                    return response;
                }).orElseGet(() -> {
                    response.setStatus(false);
                    response.getMessages().add("Privilege not found");
                    return response;
                });
    }

    public ApiResponse<Void> deletePrivilege(Integer id) {
        ApiResponse<Void> response = new ApiResponse<>();
        if (privilegeRepository.existsById(id)) {
            privilegeRepository.deleteById(id);
            response.setStatus(true);
            response.getMessages().add("Privilege deleted successfully");
        } else {
            response.setStatus(false);
            response.getMessages().add("Privilege not found");
        }
        return response;
    }
}