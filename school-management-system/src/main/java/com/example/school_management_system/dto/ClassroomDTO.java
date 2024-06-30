package com.example.school_management_system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class ClassroomDTO {

    private UUID classroomId;
    private Integer classRoom;
    private Character section;
    private List<StudentDTO> students;
}
