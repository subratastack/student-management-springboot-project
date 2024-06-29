package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.AddressDTO;
import com.example.school_management_system.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T23:06:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address addressDtoToAddress(AddressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.houseNo( dto.getHouseNo() );
        address.streetName( dto.getStreetName() );
        address.city( dto.getCity() );
        address.zipcode( dto.getZipcode() );

        return address.build();
    }

    @Override
    public AddressDTO addressToAddressDto(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.houseNo( entity.getHouseNo() );
        addressDTO.streetName( entity.getStreetName() );
        addressDTO.city( entity.getCity() );
        addressDTO.zipcode( entity.getZipcode() );

        return addressDTO.build();
    }
}
