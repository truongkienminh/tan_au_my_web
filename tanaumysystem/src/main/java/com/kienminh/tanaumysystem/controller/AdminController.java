package com.kienminh.tanaumysystem.controller;

import com.kienminh.tanaumysystem.dto.request.TeacherRegisterRequest;
import com.kienminh.tanaumysystem.entity.User;
import com.kienminh.tanaumysystem.enums.UserRole;
import com.kienminh.tanaumysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create-teacher")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherRegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username đã tồn tại");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.TEACHER);

        userRepository.save(user);
        return ResponseEntity.ok("Tạo tài khoản giáo viên thành công");
    }
}
