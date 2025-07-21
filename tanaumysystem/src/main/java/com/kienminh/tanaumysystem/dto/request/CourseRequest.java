package com.kienminh.tanaumysystem.dto.request;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CourseRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
    private List<Long> slotIds;
}