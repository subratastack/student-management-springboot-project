package com.example.school_management_system.repositories;

import com.example.school_management_system.entity.Classroom;
import com.example.school_management_system.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ClassroomRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Test
    void testCreateClassroom() {

        Student student1 = Student.builder()
                .name("STUDENT_1")
                .build();

        Student student2 = Student.builder()
                .name("STUDENT_2")
                .build();

        Student savedStudent1 = studentRepository.save(student1);
        Student savedStudent2 = studentRepository.save(student2);

        List<Student> students = Arrays.asList(savedStudent1, savedStudent2);

        Classroom classroom = Classroom.builder()
                .classRoom(1)
                .section('A')
                .students(students)
                .build();

        Classroom savedClassroom = classroomRepository.save(classroom);

        assertThat(savedClassroom).isNotNull();
        assertThat(savedClassroom.getClassroomId()).isNotNull();
        assertThat(savedClassroom.getStudents().size()).isEqualTo(students.size());
    }
}