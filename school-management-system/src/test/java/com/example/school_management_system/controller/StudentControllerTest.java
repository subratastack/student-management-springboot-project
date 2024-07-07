package com.example.school_management_system.controller;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.mappers.AddressMapper;
import com.example.school_management_system.mappers.StudentMapper;
import com.example.school_management_system.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.Random.class)
class StudentControllerTest {

    @Autowired
    StudentController studentController;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testListStudents() {
        List<StudentDTO> dtos = studentController.listStudents();

        assertThat(dtos.size()).isEqualTo(20);
    }

    @Transactional
    @Rollback
    @Test
    void testEmptyListOfStudents() {
        studentRepository.deleteAll();
        List<StudentDTO> dtos = studentController.listStudents();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testStudentGetById() {
        Student student = studentRepository.findAll().getFirst();
        StudentDTO dto = studentController.getStudentById(student.getId());

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(student.getId());
    }

    @Test
    void testStudentNotFound() {
        assertThrows(NotFoundException.class, () -> {
            studentController.getStudentById(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void saveNewStudent() {

        StudentDTO studentDTO = StudentDTO.builder()
                .name("StudentOne")
                .dob("2015-06-23")
                .build();
        ResponseEntity<StudentDTO> responseEntity = studentController.createStudent(studentDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders()).isNotNull();

        String[] locationUUID = Objects.requireNonNull(responseEntity.getHeaders().getLocation()).getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Student student = studentRepository.findById(savedUUID).get();
        assertThat(student).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void testUpdateStudentById() {
        Student student = studentRepository.findAll().getFirst();
        StudentDTO studentDTO = studentMapper.studentToStudentDto(student);
        final String NAME = "UPDATED_STUDENT";
        studentDTO.setName(NAME);

        ResponseEntity responseEntity = studentController.updateStudentById(studentDTO.getId(), studentDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Student updatedStudent = studentRepository.findById(studentDTO.getId()).get();
        assertThat(updatedStudent.getName()).isEqualTo(NAME);
    }

    @Test
    void testUpdateStudentNotFound() {
        Student student = studentRepository.findAll().getFirst();
        StudentDTO studentDTO = studentMapper.studentToStudentDto(student);
        studentDTO.setId(UUID.randomUUID());

        assertThrows(NotFoundException.class, () -> {
            studentController.updateStudentById(studentDTO.getId(), studentDTO);
        });
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteStudentById() {
        long count = studentRepository.count();
        Student student = studentRepository.findAll().getFirst();
        ResponseEntity responseEntity = studentController.deleteStudentById(student.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(studentRepository.count()).isEqualTo(count - 1);
    }

    @Test
    void testDeleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
           studentController.deleteStudentById(UUID.randomUUID());
        });
    }

}