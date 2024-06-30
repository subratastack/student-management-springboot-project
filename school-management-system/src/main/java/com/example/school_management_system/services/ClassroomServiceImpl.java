package com.example.school_management_system.services;

import com.example.school_management_system.dto.ClassroomDTO;
import com.example.school_management_system.mappers.ClassroomMapper;
import com.example.school_management_system.repositories.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    @Override
    public List<ClassroomDTO> listOfClassrooms() {
        return classroomRepository.findAll().stream()
                .map(classroomMapper::classroomToClassroomDto)
                .toList();
    }

    @Override
    public ClassroomDTO createClassroom(ClassroomDTO classroomDTO) {
        return null;
    }
}
