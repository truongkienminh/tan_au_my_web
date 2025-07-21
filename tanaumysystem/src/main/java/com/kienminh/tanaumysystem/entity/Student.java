package com.kienminh.tanaumysystem.entity;

import com.kienminh.tanaumysystem.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String school;
    private String location;
    private String phone;
    private boolean active;
    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<ExamResult> examResults;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

}


