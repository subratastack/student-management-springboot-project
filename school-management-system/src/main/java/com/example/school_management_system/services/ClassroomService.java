package com.example.school_management_system.services;

import com.example.school_management_system.dto.ClassroomDTO;

import java.util.List;

public interface ClassroomService {

    List<ClassroomDTO> listOfClassrooms();
    ClassroomDTO createClassroom(ClassroomDTO classroomDTO);
}
