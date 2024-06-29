package com.example.school_management_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class StudentDTO {

    private UUID id;

    @JsonProperty("parent_name")
    private String parentName;

    @NotNull
    @NotBlank
    private String name;
    private String dob;
    private String email;
    private String gender;
    private AddressDTO address;

    @JsonProperty("phone_no")
    private String phoneNo;
    private String password;
}
