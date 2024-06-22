package com.example.school_management_system.entity;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String houseNo;
    private String streetName;
    private String city;
    private String zipcode;
}
