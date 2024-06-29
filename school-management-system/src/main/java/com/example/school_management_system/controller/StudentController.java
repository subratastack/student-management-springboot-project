package com.example.school_management_system.controller;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StudentController {

    public static final String STUDENT_PATH = "/api/v1/student";
    public static final String STUDENT_PATH_ID = STUDENT_PATH + "/{studentId}";

    private final StudentService studentService;

    @GetMapping(STUDENT_PATH)
    public List<StudentDTO> listStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = STUDENT_PATH_ID)
    public StudentDTO getStudentById(@PathVariable("studentId") UUID studentId) {
        log.debug("getStudentById - StudentController");
        return studentService.getStudentById(studentId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = STUDENT_PATH)
    public ResponseEntity<StudentDTO> createStudent(@Validated @RequestBody StudentDTO studentDTO) {
        StudentDTO savedStudent = studentService.createStudent(studentDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", STUDENT_PATH + "/" + savedStudent.getStudentId().toString());

        return ResponseEntity
                .created(URI.create(Objects.requireNonNull(headers.getFirst("Location"))))
                .body(savedStudent);
    }

    @PutMapping(STUDENT_PATH_ID)
    public ResponseEntity updateById(@PathVariable("studentId") UUID studentId,
                                     @Validated @RequestBody StudentDTO studentDTO) {

        if (studentService.updateStudentById(studentId, studentDTO).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
