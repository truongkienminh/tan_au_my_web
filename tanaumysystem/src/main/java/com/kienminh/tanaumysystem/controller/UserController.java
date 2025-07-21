package com.kienminh.tanaumysystem.controller;

import com.kienminh.tanaumysystem.dto.request.ChangePasswordDTO;
import com.kienminh.tanaumysystem.dto.request.UserUpdateRequest;
import com.kienminh.tanaumysystem.entity.User;
import com.kienminh.tanaumysystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody @Valid ChangePasswordDTO dto) {
        userService.changePassword(dto);
        return "Đổi mật khẩu thành công!";
    }
}
