package com.kienminh.tanaumysystem.dto.request;

import com.kienminh.tanaumysystem.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentUpdateRequest {
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String school;
    private String location;
    private String phone;
    private boolean active;
}
