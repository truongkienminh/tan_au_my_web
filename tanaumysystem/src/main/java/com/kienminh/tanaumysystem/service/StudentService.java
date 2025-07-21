package com.kienminh.tanaumysystem.service;

import com.kienminh.tanaumysystem.dto.request.StudentUpdateRequest;
import com.kienminh.tanaumysystem.entity.Student;
import com.kienminh.tanaumysystem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    public Student updateStudent(Long id, StudentUpdateRequest dto) {
        Student student = getStudentById(id);
        student.setFullName(dto.getFullName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setSchool(dto.getSchool());
        student.setLocation(dto.getLocation());
        student.setPhone(dto.getPhone());
        student.setActive(dto.isActive());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
