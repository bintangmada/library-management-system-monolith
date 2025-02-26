package com.library_management_system_monolith.controller;

import com.library_management_system_monolith.dto.RegistrationDto;
import com.library_management_system_monolith.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

//    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public String registerUser(@RequestBody RegistrationDto request) {
        return registrationService.registerUser(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
