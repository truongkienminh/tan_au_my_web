package com.kienminh.tanaumysystem.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String teacherName;
    private List<String> slots;
}
