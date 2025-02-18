package com.library_management_system_monolith.service;

import com.library_management_system_monolith.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


}
