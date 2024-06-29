package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student studentDtoToStudent(StudentDTO dto);
    StudentDTO studentToStudentDto(Student entity);
}
