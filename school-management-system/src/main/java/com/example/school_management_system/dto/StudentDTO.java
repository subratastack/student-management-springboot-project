package com.example.school_management_system.dto;

import com.example.school_management_system.entity.Address;
import com.example.school_management_system.enums.GenderTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class StudentDTO {

    private UUID studentId;
    private String parentName;

    @NotNull
    @NotBlank
    private String name;
    private LocalDate dob;
    private String email;
    private String gender;
    private Address address;
    private String phoneNo;
}
