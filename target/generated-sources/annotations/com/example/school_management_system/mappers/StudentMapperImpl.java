package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T19:08:47+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student studentDtoToStudent(StudentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Student.StudentBuilder<?, ?> student = Student.builder();

        student.name( dto.getName() );
        student.dob( dto.getDob() );
        student.email( dto.getEmail() );
        student.gender( dto.getGender() );
        student.address( dto.getAddress() );
        student.phoneNo( dto.getPhoneNo() );
        student.studentId( dto.getStudentId() );
        student.parentName( dto.getParentName() );

        return student.build();
    }

    @Override
    public StudentDTO studentToStudentDto(Student entity) {
        if ( entity == null ) {
            return null;
        }

        StudentDTO.StudentDTOBuilder studentDTO = StudentDTO.builder();

        studentDTO.studentId( entity.getStudentId() );
        studentDTO.parentName( entity.getParentName() );
        studentDTO.name( entity.getName() );
        studentDTO.dob( entity.getDob() );
        studentDTO.email( entity.getEmail() );
        studentDTO.gender( entity.getGender() );
        studentDTO.address( entity.getAddress() );
        studentDTO.phoneNo( entity.getPhoneNo() );

        return studentDTO.build();
    }
}
