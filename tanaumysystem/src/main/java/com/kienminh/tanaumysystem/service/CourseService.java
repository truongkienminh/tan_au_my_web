package com.kienminh.tanaumysystem.service;


import com.kienminh.tanaumysystem.dto.request.CourseRequest;
import com.kienminh.tanaumysystem.entity.Course;
import com.kienminh.tanaumysystem.entity.Slot;
import com.kienminh.tanaumysystem.entity.Teacher;
import com.kienminh.tanaumysystem.repository.CourseRepository;
import com.kienminh.tanaumysystem.repository.SlotRepository;
import com.kienminh.tanaumysystem.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final SlotRepository slotRepository;

    public Course createCourse(CourseRequest dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStartDate(dto.getStartDate());
        course.setEndDate(dto.getEndDate());

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        List<Slot> slots = slotRepository.findAllById(dto.getSlotIds());

        course.setTeacher(teacher);
        course.setSlots(slots);

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseRequest dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setStartDate(dto.getStartDate());
        course.setEndDate(dto.getEndDate());

        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        List<Slot> slots = slotRepository.findAllById(dto.getSlotIds());

        course.setTeacher(teacher);
        course.setSlots(slots);

        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }
}
