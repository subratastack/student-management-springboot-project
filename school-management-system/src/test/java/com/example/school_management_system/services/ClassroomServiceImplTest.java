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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    private List<StudentDTO> mockStudents;
    private List<StudentDTO> mockStudents2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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

        mockStudents = Arrays.asList(
                studentDto1,
                studentDto2
        );
        mockStudents2 = Collections.singletonList(
                studentDto3
        );
    }

    @Test
    void testGetAllClassroom() {

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

    @Test
    void testCreateClassroom() {

        final UUID CLASSROOM_ID = UUID.randomUUID();
        final Integer CLASSROOM = 1;
        final Character CLASSROOM_SECTION = 'A';

        ClassroomDTO classroomDTO = ClassroomDTO.builder()
                .classroomId(CLASSROOM_ID)
                .classRoom(CLASSROOM)
                .section(CLASSROOM_SECTION)
                .build();

        Classroom classroom = Classroom.builder()
                .classroomId(CLASSROOM_ID)
                .classRoom(CLASSROOM)
                .section(CLASSROOM_SECTION)
                .build();


        when(classroomRepository.save(classroomMapper.classroomDtoToClassroom(classroomDTO)))
                .thenReturn(classroom);
        when(classroomMapper.classroomToClassroomDto(classroom)).thenReturn(classroomDTO);

        ClassroomDTO savedClassroomDto = classroomService.createClassroom(classroomDTO);

        assertEquals(classroomDTO.getClassroomId(), savedClassroomDto.getClassroomId());
        assertEquals(classroomDTO.getClassRoom(), savedClassroomDto.getClassRoom());

    }

    @Test
    void testGetClassroomById() {

        final UUID CLASSROOM_ID = UUID.randomUUID();
        ClassroomDTO classroomDTO = ClassroomDTO.builder()
                .classroomId(CLASSROOM_ID)
                .classRoom(1)
                .section('A')
                .build();

        when(classroomRepository.findById(CLASSROOM_ID)).thenReturn(Optional.of(new Classroom()));
        when(classroomMapper.classroomToClassroomDto(Mockito.any(Classroom.class)))
                .thenReturn(classroomDTO);

        Optional<ClassroomDTO> existingClassroom = classroomService.getClassroomById(CLASSROOM_ID);

        assertEquals(existingClassroom.get(), classroomDTO);
        assertThat(existingClassroom.get().getClassroomId()).isEqualTo(classroomDTO.getClassroomId());
    }

    @Test
    void testGetClassroomByIdNull() {
        final UUID CLASSROOM_ID = UUID.randomUUID();

        when(classroomRepository.findById(CLASSROOM_ID)).thenReturn(Optional.empty());
        when(classroomMapper.classroomToClassroomDto(Mockito.any(Classroom.class)))
                .thenReturn(null);

        Optional<ClassroomDTO> classroomDTO = classroomService.getClassroomById(CLASSROOM_ID);

        assertTrue(classroomDTO.isEmpty());
    }
}