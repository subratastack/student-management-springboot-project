package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.AddressDTO;
import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Address;
import com.example.school_management_system.entity.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-06T18:09:48+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student studentDtoToStudent(StudentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Student.StudentBuilder<?, ?> student = Student.builder();

        student.id( dto.getId() );
        student.name( dto.getName() );
        if ( dto.getDob() != null ) {
            student.dob( LocalDate.parse( dto.getDob() ) );
        }
        student.email( dto.getEmail() );
        student.password( dto.getPassword() );
        student.gender( dto.getGender() );
        student.address( addressDTOToAddress( dto.getAddress() ) );
        student.phoneNo( dto.getPhoneNo() );
        student.parentName( dto.getParentName() );

        return student.build();
    }

    @Override
    public StudentDTO studentToStudentDto(Student entity) {
        if ( entity == null ) {
            return null;
        }

        StudentDTO.StudentDTOBuilder studentDTO = StudentDTO.builder();

        studentDTO.id( entity.getId() );
        studentDTO.parentName( entity.getParentName() );
        studentDTO.name( entity.getName() );
        if ( entity.getDob() != null ) {
            studentDTO.dob( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getDob() ) );
        }
        studentDTO.email( entity.getEmail() );
        studentDTO.gender( entity.getGender() );
        studentDTO.address( addressToAddressDTO( entity.getAddress() ) );
        studentDTO.phoneNo( entity.getPhoneNo() );
        studentDTO.password( entity.getPassword() );

        return studentDTO.build();
    }

    protected Address addressDTOToAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.houseNo( addressDTO.getHouseNo() );
        address.streetName( addressDTO.getStreetName() );
        address.city( addressDTO.getCity() );
        address.zipcode( addressDTO.getZipcode() );

        return address.build();
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.houseNo( address.getHouseNo() );
        addressDTO.streetName( address.getStreetName() );
        addressDTO.city( address.getCity() );
        addressDTO.zipcode( address.getZipcode() );

        return addressDTO.build();
    }
}
