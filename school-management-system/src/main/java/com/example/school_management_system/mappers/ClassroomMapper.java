package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.ClassroomDTO;
import com.example.school_management_system.entity.Classroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    public Classroom classroomDtoToClassroom(ClassroomDTO dto);
    public ClassroomDTO classroomToClassroomDto(Classroom entity);
}
