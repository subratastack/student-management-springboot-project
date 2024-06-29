package com.example.school_management_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDTO {

    @JsonProperty("house_no")
    private String houseNo;
    @JsonProperty("street_name")
    private String streetName;
    private String city;
    private String zipcode;
}
