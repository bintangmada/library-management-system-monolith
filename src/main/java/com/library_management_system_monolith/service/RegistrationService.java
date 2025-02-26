package com.library_management_system_monolith.service;

import com.library_management_system_monolith.dto.RegistrationDto;
import com.library_management_system_monolith.entity.Role;
import com.library_management_system_monolith.entity.User;
import com.library_management_system_monolith.entity.UserConfirmationToken;
import com.library_management_system_monolith.repository.UserRepository;
import com.library_management_system_monolith.util.EmailSender;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class RegistrationService {
    private final UserService userService;
    private final RoleService roleService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final UserRepository userRepository;

    public RegistrationService(UserService userService, RoleService roleService, EmailValidator emailValidator, ConfirmationTokenService confirmationTokenService, EmailSender emailSender, TemplateEngine templateEngine, UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.userRepository = userRepository;
    }

    // Enum untuk menentukan tipe user
    public enum UserType {
        EMPLOYEE(1, "Employee"),
        ADMIN(2, "Admin"),
        SUPERUSER(3, "Super User");

        private final int roleId;
        private final String roleName;

        UserType(int roleId, String roleName) {
            this.roleId = roleId;
            this.roleName = roleName;
        }

        public int getRoleId() {
            return roleId;
        }

        public String getRoleName() {
            return roleName;
        }
    }

    public String registerUser(RegistrationDto registrationDto) {
        // Validasi email
        if (!emailValidator.test(registrationDto.getEmail())) {
            throw new IllegalStateException("Email tidak valid");
        }

        // Membuat User
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());

        // Set Role berdasarkan UserType
        List<Role> roles = new ArrayList<>();
//        roles.add(roleService.getById(userType.getRoleId()));
//        user.setRoles(roles);

        // Simpan user dan dapatkan token
        String token = userService.signUpUser(user);

        // Kirim email konfirmasi
        sendConfirmationEmail(registrationDto, token);

        return token;
    }

//    public String registerUser(RegistrationDto registrationDto, UserType userType) {
//        // Validasi email
//        if (!emailValidator.test(registrationDto.getEmail())) {
//            throw new IllegalStateException("Email tidak valid");
//        }
//
//        // Membuat User
//        User user = new User();
//        user.setUsername(registrationDto.getUsername());
//        user.setPassword(registrationDto.getPassword());
//        user.setEmail(registrationDto.getEmail());
//
//        // Set Role berdasarkan UserType
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleService.getById(userType.getRoleId()));
////        user.setRoles(roles);
//
//        // Simpan user dan dapatkan token
//        String token = userService.signUpUser(user);
//
//        // Kirim email konfirmasi
//        sendConfirmationEmail(registrationDto, userType.getRoleName(), token);
//
//        return token;
//    }

    private void sendConfirmationEmail(RegistrationDto registrationDto, String token) {
        String link = "http://localhost:8088/api/registration/confirm?token=" + token;
        Context ctx = new Context();
        String emailPrefix = registrationDto.getEmail().split("@")[0];
        ctx.setVariable("first_name", "Hi " + emailPrefix);
        ctx.setVariable("username", "Username : " + registrationDto.getUsername());
        ctx.setVariable("password", "Password : " + registrationDto.getPassword());
        ctx.setVariable("confirmation_link", link);

        String htmlContent = templateEngine.process("template_registration", ctx);
//        String subject = "Activate Your " + roleName + " Account";
        String subject = "Activate Your Account";

        try {
            emailSender.send(registrationDto.getEmail(), subject, htmlContent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    private void sendConfirmationEmail(RegistrationDto registrationDto, String roleName, String token) {
//        String link = "http://localhost:8088/api/registration/confirm?token=" + token;
//        Context ctx = new Context();
//        String emailPrefix = registrationDto.getEmail().split("@")[0];
//        ctx.setVariable("first_name", "Hi " + emailPrefix);
//        ctx.setVariable("username", "Username : " + registrationDto.getUsername());
//        ctx.setVariable("password", "Password : " + registrationDto.getPassword());
//        ctx.setVariable("confirmation_link", link);
//
//        String htmlContent = templateEngine.process("template_registration", ctx);
//        String subject = "Activate Your " + roleName + " Account";
//
//        try {
//            emailSender.send(registrationDto.getEmail(), subject, htmlContent);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Transactional
    public String confirmToken(String token) {
        UserConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token Not Found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email Already Confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token Expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableUser(
                confirmationToken.getUserId());
        Context ctx = new Context();
        return templateEngine.process("account_activated", ctx);
    }
}
