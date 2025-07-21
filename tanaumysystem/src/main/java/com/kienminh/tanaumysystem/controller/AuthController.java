package com.kienminh.tanaumysystem.controller;

import com.kienminh.tanaumysystem.dto.request.LoginRequest;
import com.kienminh.tanaumysystem.dto.request.RegisterRequest;
import com.kienminh.tanaumysystem.dto.response.LoginResponse;
import com.kienminh.tanaumysystem.entity.User;
import com.kienminh.tanaumysystem.enums.UserRole;
import com.kienminh.tanaumysystem.repository.UserRepository;
import com.kienminh.tanaumysystem.service.EmailService;
import com.kienminh.tanaumysystem.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.STUDENT);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        emailService.sendRegistrationSuccess(user.getUsername(), user.getEmail());
        return ResponseEntity.ok("Đăng ký thành công");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(loginRequest.getUsername()).get();
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}

