package com.kienminh.tanaumysystem.entity;

import com.kienminh.tanaumysystem.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private Gender gender;
    private boolean active;


    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

}

