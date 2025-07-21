package com.kienminh.tanaumysystem.dto.response;

import com.kienminh.tanaumysystem.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String school;
    private String location;
    private String phone;
    private boolean active;
    private String username; // từ User
}
