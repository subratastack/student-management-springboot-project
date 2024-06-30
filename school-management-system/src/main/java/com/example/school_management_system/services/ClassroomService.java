package com.example.school_management_system.services;

import com.example.school_management_system.dto.ClassroomDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassroomService {

    List<ClassroomDTO> listOfClassrooms();
    ClassroomDTO createClassroom(ClassroomDTO classroomDTO);
    Optional<ClassroomDTO> getClassroomById(UUID classroomId);
    Optional<ClassroomDTO> updateClassroomById(UUID classroomId, ClassroomDTO classroomDTO);
}
