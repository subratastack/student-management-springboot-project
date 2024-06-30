package com.example.school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Student extends Person {

    private String parentName;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}
