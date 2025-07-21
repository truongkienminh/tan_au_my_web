package com.kienminh.tanaumysystem.dto.request;

import lombok.Data;

@Data
public class TeacherRegisterRequest {
    private String username;
    private String password;
    private String email;
}
