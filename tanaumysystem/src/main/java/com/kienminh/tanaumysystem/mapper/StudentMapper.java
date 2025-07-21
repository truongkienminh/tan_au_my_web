package com.kienminh.tanaumysystem.mapper;

import com.kienminh.tanaumysystem.dto.response.StudentResponse;
import com.kienminh.tanaumysystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "username", source = "user.username")
    StudentResponse toDTO(Student student);
}
