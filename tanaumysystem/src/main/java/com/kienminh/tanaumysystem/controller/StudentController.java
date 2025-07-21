package com.kienminh.tanaumysystem.controller;

import com.kienminh.tanaumysystem.dto.request.StudentUpdateRequest;
import com.kienminh.tanaumysystem.dto.response.StudentResponse;
import com.kienminh.tanaumysystem.entity.Student;
import com.kienminh.tanaumysystem.mapper.StudentMapper;
import com.kienminh.tanaumysystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return studentMapper.toDTO(student);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequest dto) {
        Student updated = studentService.updateStudent(id, dto);
        return studentMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
