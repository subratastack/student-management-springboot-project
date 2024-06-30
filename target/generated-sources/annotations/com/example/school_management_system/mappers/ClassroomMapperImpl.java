package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.ClassroomDTO;
import com.example.school_management_system.entity.Classroom;
import com.example.school_management_system.entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-30T11:19:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ClassroomMapperImpl implements ClassroomMapper {

    @Override
    public Classroom classroomDtoToClassroom(ClassroomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Classroom.ClassroomBuilder classroom = Classroom.builder();

        classroom.classRoom( dto.getClassRoom() );
        classroom.section( dto.getSection() );
        List<Student> list = dto.getStudents();
        if ( list != null ) {
            classroom.students( new ArrayList<Student>( list ) );
        }

        return classroom.build();
    }

    @Override
    public ClassroomDTO classroomToClassroomDto(Classroom entity) {
        if ( entity == null ) {
            return null;
        }

        ClassroomDTO.ClassroomDTOBuilder classroomDTO = ClassroomDTO.builder();

        classroomDTO.classRoom( entity.getClassRoom() );
        classroomDTO.section( entity.getSection() );
        List<Student> list = entity.getStudents();
        if ( list != null ) {
            classroomDTO.students( new ArrayList<Student>( list ) );
        }

        return classroomDTO.build();
    }
}
