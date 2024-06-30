package com.example.school_management_system.entity;

import com.example.school_management_system.enums.GenderTypes;
import com.example.school_management_system.interfaces.Authentication;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;


@Data
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person implements Authentication {

    /*
    * @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )*/

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Embedded
    private Address address;
    private String phoneNo;

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
