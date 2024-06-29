package com.example.school_management_system.entity;

import com.example.school_management_system.enums.GenderTypes;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
    @Size(max=50)
    @Column(length = 50)
    private String name;

    @Past
    private LocalDate dob;

    @Email
    private String email;
    private String password;
    private String gender;
    private Address address;
    private String phoneNo;
}
