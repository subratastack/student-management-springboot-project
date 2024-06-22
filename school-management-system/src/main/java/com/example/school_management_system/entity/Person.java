package com.example.school_management_system.entity;

import com.example.school_management_system.enums.GenderTypes;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotNull
    @NotBlank
    @Size(max=20)
    @Column(length = 20)
    private String name;
    private LocalDate dob;
    private String email;
    private String password;
    private String gender;
    private Address address;
    private String phoneNo;
}
