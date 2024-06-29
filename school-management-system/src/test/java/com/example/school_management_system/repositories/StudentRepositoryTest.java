package com.example.school_management_system.repositories;

import com.example.school_management_system.entity.Student;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void saveStudentTest() {

        String STUDENT_NAME = "Ipsita Mishra";

        Student savedStudent = studentRepository.save(
                Student.builder()
                        .name(STUDENT_NAME)
                        .dob(LocalDate.of(1991, 2, 23))
                        .parentName("Tapan Mishra")
                        .build());

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getStudentId()).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo(STUDENT_NAME);
    }

    @Test
    void studentLongNameTest() {
        String STUDENT_NAME = "hdhdfadhjkshjkadshjkashjkadshkjadshjbnmdfsbghjhjdfkasghjdfsghjdfsjghdfs";

        assertThrows(ConstraintViolationException.class, () -> {
            Student savedStudent = studentRepository.save(
                    Student.builder()
                            .name(STUDENT_NAME)
                            .dob(LocalDate.of(1990, 5, 10))
                            .build());

            studentRepository.flush();
        });
    }

    @Test
    void studentPastDateDobValidationTest() {

    }

}