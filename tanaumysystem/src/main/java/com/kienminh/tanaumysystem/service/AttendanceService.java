package com.kienminh.tanaumysystem.service;

import com.kienminh.tanaumysystem.entity.Attendance;
import com.kienminh.tanaumysystem.entity.Course;
import com.kienminh.tanaumysystem.entity.Student;
import com.kienminh.tanaumysystem.repository.AttendanceRepository;
import com.kienminh.tanaumysystem.repository.CourseRepository;
import com.kienminh.tanaumysystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    /**
     * Ghi nhận điểm danh cho học sinh theo khóa học vào ngày cụ thể
     * @param studentId id học sinh
     * @param courseId id khóa học
     * @param present true nếu có mặt, false nếu vắng
     * @return Attendance đã lưu
     */
    public Attendance mark(Long studentId, Long courseId, boolean present) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy học sinh"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khóa học"));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setPresent(present);
        attendance.setDate(LocalDate.now());

        return attendanceRepository.save(attendance);
    }
}
