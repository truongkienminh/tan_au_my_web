package com.kienminh.tanaumysystem.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserUpdateRequest {
    private String username;
    private String email;
    private String password;
}
