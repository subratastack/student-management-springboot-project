package com.example.school_management_system.services;

import com.example.school_management_system.dto.ClassroomDTO;
import com.example.school_management_system.entity.Classroom;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.mappers.ClassroomMapper;
import com.example.school_management_system.mappers.StudentMapper;
import com.example.school_management_system.repositories.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;
    private final StudentMapper studentMapper;

    @Override
    public List<ClassroomDTO> listOfClassrooms() {
        return classroomRepository.findAll().stream()
                .map(classroomMapper::classroomToClassroomDto)
                .toList();
    }

    @Override
    public ClassroomDTO createClassroom(ClassroomDTO classroomDTO) {
        Classroom savedClassroom = classroomRepository.save(
                classroomMapper.classroomDtoToClassroom(classroomDTO)
        );

        return classroomMapper.classroomToClassroomDto(savedClassroom);
    }

    @Override
    public Optional<ClassroomDTO> getClassroomById(UUID classroomId) {
        return Optional.ofNullable(
                classroomMapper.classroomToClassroomDto(
                        classroomRepository.findById(classroomId)
                                .orElse(null))
        );
    }

    @Override
    public Optional<ClassroomDTO> updateClassroomById(UUID classroomId, ClassroomDTO classroomDTO) {

        AtomicReference<Optional<ClassroomDTO>> atomicReference = new AtomicReference<>();

        classroomRepository.findById(classroomId).ifPresentOrElse(classroomFound -> {
            classroomFound.setClassRoom(classroomDTO.getClassRoom());
            classroomFound.setSection(classroomDTO.getSection());

            List<Student> updatedStudents = classroomDTO.getStudents().stream()
                    .map(studentMapper::studentDtoToStudent)
                    .toList();
            classroomFound.setStudents(updatedStudents);
            atomicReference.set(Optional.ofNullable(
                    classroomMapper.classroomToClassroomDto(
                            classroomRepository.save(classroomFound)
                    )
            ));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
}
