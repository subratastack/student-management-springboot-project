package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID classroomId;
    private Integer classRoom;
    private Character section;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> students;
}
