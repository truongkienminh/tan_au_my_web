package com.kienminh.tanaumysystem.controller;

import com.kienminh.tanaumysystem.dto.request.CourseRequest;
import com.kienminh.tanaumysystem.dto.response.CourseResponse;
import com.kienminh.tanaumysystem.entity.Course;
import com.kienminh.tanaumysystem.mapper.CourseMapper;
import com.kienminh.tanaumysystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public CourseResponse createCourse(@RequestBody CourseRequest dto) {
        Course saved = courseService.createCourse(dto);
        return courseMapper.toDTO(saved);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest dto) {
        Course updated = courseService.updateCourse(id, dto);
        return courseMapper.toDTO(updated);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return courseMapper.toDTO(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
