package com.example.school_management_system.services;

import com.example.school_management_system.dto.ClassroomDTO;
import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Classroom;
import com.example.school_management_system.mappers.ClassroomMapper;
import com.example.school_management_system.repositories.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClassroomServiceImplTest {

    @Autowired
    private ClassroomServiceImpl classroomService;

    @MockBean
    private ClassroomRepository classroomRepository;

    @MockBean
    private ClassroomMapper classroomMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClassroom() {
        StudentDTO studentDto1 = StudentDTO.builder()
                .id(UUID.randomUUID())
                .name("StudentTest1")
                .build();
        StudentDTO studentDto2 = StudentDTO.builder()
                .id(UUID.randomUUID())
                .name("StudentTest2")
                .build();
        StudentDTO studentDto3 = StudentDTO.builder()
                .id(UUID.randomUUID())
                .name("StudentTest3")
                .build();

        List<StudentDTO> mockStudents = Arrays.asList(
                studentDto1,
                studentDto2
        );
        List<StudentDTO> mockStudents2 = Collections.singletonList(
                studentDto3
        );

        ClassroomDTO classroomDTO1 = ClassroomDTO.builder()
                .classroomId(UUID.randomUUID())
                .classRoom(1)
                .section('A')
                .students(mockStudents)
                .build();

        ClassroomDTO classroomDTO2 = ClassroomDTO.builder()
                .classroomId(UUID.randomUUID())
                .classRoom(2)
                .section('B')
                .students(mockStudents2)
                .build();

        List<ClassroomDTO> mockClassroomDto = Arrays.asList(classroomDTO1, classroomDTO2);

        // Mock repository method
        when(classroomRepository.findAll()).thenReturn(Arrays.asList(new Classroom(), new Classroom()));

        // Mock wrapper method
        when(classroomMapper.classroomToClassroomDto(Mockito.any(Classroom.class)))
                .thenReturn(classroomDTO1).thenReturn(classroomDTO2);

        List<ClassroomDTO> results = classroomService.listOfClassrooms();

        assertEquals(mockClassroomDto.size(), results.size());
        assertEquals(mockClassroomDto.getFirst().getClassroomId(), results.getFirst().getClassroomId());
        assertEquals(mockClassroomDto.getLast().getStudents().size(), results.getLast().getStudents().size());
    }

    @Test
    void testGetAllClassroomNoResult() {
        when(classroomRepository.findAll()).thenReturn(List.of());
        when(classroomMapper.classroomToClassroomDto(Mockito.any())).thenReturn(null);

        List<ClassroomDTO> results = classroomService.listOfClassrooms();

        assertEquals(results.size(), 0);
    }
}