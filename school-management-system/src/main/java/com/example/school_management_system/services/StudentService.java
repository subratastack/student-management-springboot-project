package com.example.school_management_system.services;

import com.example.school_management_system.dto.StudentDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {

    StudentDTO createStudent(StudentDTO dto);
    Optional<StudentDTO> getStudentById(UUID id);
    List<StudentDTO> getAllStudents();
    Optional<StudentDTO> updateStudentById(UUID id, StudentDTO dto);
    Boolean deleteStudentById(UUID studentId);
}
