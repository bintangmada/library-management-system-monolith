//package com.library_management_system_monolith.controller;
//
//import com.library_management_system_monolith.dto.RegistrationDto;
//import com.library_management_system_monolith.service.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
//        this.userService = userService;
//        this.jwtUtil = jwtUtil;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegistrationDto registrationDto) {
//        userService.registerUser(registrationDto);
//        return ResponseEntity.ok("User registered successfully!");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
//        );
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = jwtUtil.generateToken(userDetails.getUsername());
//
//        return ResponseEntity.ok(token);
//    }
//}
