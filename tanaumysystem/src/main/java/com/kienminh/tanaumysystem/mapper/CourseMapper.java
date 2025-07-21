package com.kienminh.tanaumysystem.mapper;

import com.kienminh.tanaumysystem.dto.response.CourseResponse;
import com.kienminh.tanaumysystem.entity.Course;
import com.kienminh.tanaumysystem.entity.Slot;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "teacherName", source = "teacher.user.username")
    @Mapping(target = "slots", expression = "java(mapSlots(course.getSlots()))")
    CourseResponse toDTO(Course course);

    default List<String> mapSlots(List<Slot> slots) {
        return slots.stream()
                .map(slot -> slot.getDayOfWeek() + " " + slot.getTimeRange())
                .collect(Collectors.toList());
    }
}
