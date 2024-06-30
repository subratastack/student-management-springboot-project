package com.example.school_management_system.services;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.mappers.StudentMapper;
import com.example.school_management_system.repositories.StudentRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudents() {

        StudentDTO studentDto1 = StudentDTO.builder()
                .id(UUID.randomUUID())
                .name("StudentTest1")
                .build();
        StudentDTO studentDto2 = StudentDTO.builder()
                .id(UUID.randomUUID())
                .name("StudentTest2")
                .build();

        List<StudentDTO> mockStudents = Arrays.asList(
            studentDto1,
            studentDto2
        );

        // Mock repository method
        when(studentRepository.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));

        // Mock wrapper method
        when(studentMapper.studentToStudentDto(Mockito.any(Student.class)))
                .thenReturn(studentDto1).thenReturn(studentDto2);

        List<StudentDTO> results =  studentServiceImpl.getAllStudents();

        assertEquals(mockStudents.size(), results.size());
        assertEquals(mockStudents.get(0).getId(), results.get(0).getId());
        assertEquals(mockStudents.get(0).getName(), results.get(0).getName());
        assertEquals(mockStudents.get(1).getId(), results.get(1).getId());
        assertEquals(mockStudents.get(1).getName(), results.get(1).getName());
    }

    @Test
    void getStudentByIdTest() {

        UUID STUDENT_ID = UUID.randomUUID();
        StudentDTO studentDto1 = StudentDTO.builder()
                .id(STUDENT_ID)
                .name("StudentTest1")
                .build();

        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(new Student()));

        when(studentMapper.studentToStudentDto(Mockito.any(Student.class)))
                .thenReturn(studentDto1);

        Optional<StudentDTO> result = studentServiceImpl.getStudentById(STUDENT_ID);

        assertEquals(result.get(), studentDto1);
        assertEquals(result.get().getId(), studentDto1.getId());
    }

    @Test
    void getStudentByIdNullTest() {
        UUID STUDENT_ID = UUID.randomUUID();

        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());

        when(studentMapper.studentToStudentDto(Mockito.any(Student.class)))
                .thenReturn(null);

        Optional<StudentDTO> result = studentServiceImpl.getStudentById(STUDENT_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void createStudentTest() {

        UUID STUDENT_ID = UUID.randomUUID();
        String studentName = "Student1";
        StudentDTO studentDTO = StudentDTO.builder()
                .id(STUDENT_ID)
                .name(studentName)
                .build();

        Student student = Student.builder()
                .id(STUDENT_ID)
                .name(studentName)
                .build();

        when(studentRepository.save(studentMapper.studentDtoToStudent(studentDTO))).thenReturn(student);
        when(studentMapper.studentToStudentDto(student)).thenReturn(studentDTO);
        StudentDTO createdStudent = studentServiceImpl.createStudent(studentDTO);

        assertEquals(studentDTO.getId(), createdStudent.getId());
        assertEquals(studentDTO.getName(), createdStudent.getName());

    }

    @Test
    void updateStudentByIdTest() {
        UUID STUDENT_ID = UUID.randomUUID();
        String studentName = "Student1";
        StudentDTO studentDTO = StudentDTO.builder()
                .id(STUDENT_ID)
                .name(studentName + "New")
                .dob("2015-11-25")
                .build();

        Student studentExisting = Student.builder()
                .id(STUDENT_ID)
                .name(studentName)
                .build();


        Student studentUpdated = Student.builder()
                .id(STUDENT_ID)
                .name(studentName + "New")
                .build();

        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(studentExisting));
        when(studentRepository.save(any())).thenReturn(studentUpdated);

        when(studentMapper.studentDtoToStudent(studentDTO)).thenReturn(studentUpdated);
        when(studentMapper.studentToStudentDto(studentUpdated)).thenReturn(studentDTO);
        Optional<StudentDTO> updatedStudent = studentServiceImpl.updateStudentById(STUDENT_ID, studentDTO);

        assertTrue(updatedStudent.isPresent());
        assertEquals(studentDTO.getId(), updatedStudent.get().getId());
        assertEquals(studentDTO.getName(), updatedStudent.get().getName());

    }


    @Test
    void updateStudentByIdNullTest() {

        UUID STUDENT_ID = UUID.randomUUID();
        String studentName = "Student1";
        StudentDTO studentDTO = StudentDTO.builder()
                .id(STUDENT_ID)
                .name(studentName + "New")
                .build();

        Student studentUpdated = Student.builder()
                .id(STUDENT_ID)
                .name(studentName + "New")
                .build();

        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());
        when(studentMapper.studentDtoToStudent(studentDTO)).thenReturn(studentUpdated);
        when(studentMapper.studentToStudentDto(null)).thenReturn(null);

        Optional<StudentDTO> updatedStudent = studentServiceImpl.updateStudentById(STUDENT_ID, studentDTO);

        assertTrue(updatedStudent.isEmpty());
    }

    @Test
    void deleteStudentByIdTest() {
        UUID studentId = UUID.randomUUID();

        doNothing().when(studentRepository).deleteById(studentId);
        when(studentRepository.existsById(studentId)).thenReturn(true);

        boolean result = studentServiceImpl.deleteStudentById(studentId);

        assertTrue(result);

        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    void deleteStudentByIdNotFoundTest() {

        UUID studentId = UUID.randomUUID();

        when(studentRepository.existsById(studentId)).thenReturn(false);
        boolean result = studentServiceImpl.deleteStudentById(studentId);

        assertFalse(result);
        verify(studentRepository, never()).deleteById(studentId);
    }

}