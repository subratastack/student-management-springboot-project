package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.AddressDTO;
import com.example.school_management_system.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address addressDtoToAddress(AddressDTO dto);
    AddressDTO addressToAddressDto(Address entity);
}
