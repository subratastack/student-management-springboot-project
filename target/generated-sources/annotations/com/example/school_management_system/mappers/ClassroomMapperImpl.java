package com.example.school_management_system.mappers;

import com.example.school_management_system.dto.AddressDTO;
import com.example.school_management_system.dto.ClassroomDTO;
import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.entity.Address;
import com.example.school_management_system.entity.Classroom;
import com.example.school_management_system.entity.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-06T18:09:48+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ClassroomMapperImpl implements ClassroomMapper {

    @Override
    public Classroom classroomDtoToClassroom(ClassroomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Classroom.ClassroomBuilder classroom = Classroom.builder();

        classroom.classroomId( dto.getClassroomId() );
        classroom.classRoom( dto.getClassRoom() );
        classroom.section( dto.getSection() );
        classroom.students( studentDTOListToStudentList( dto.getStudents() ) );

        return classroom.build();
    }

    @Override
    public ClassroomDTO classroomToClassroomDto(Classroom entity) {
        if ( entity == null ) {
            return null;
        }

        ClassroomDTO.ClassroomDTOBuilder classroomDTO = ClassroomDTO.builder();

        classroomDTO.classroomId( entity.getClassroomId() );
        classroomDTO.classRoom( entity.getClassRoom() );
        classroomDTO.section( entity.getSection() );
        classroomDTO.students( studentListToStudentDTOList( entity.getStudents() ) );

        return classroomDTO.build();
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

    protected Student studentDTOToStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student.StudentBuilder<?, ?> student = Student.builder();

        student.id( studentDTO.getId() );
        student.name( studentDTO.getName() );
        if ( studentDTO.getDob() != null ) {
            student.dob( LocalDate.parse( studentDTO.getDob() ) );
        }
        student.email( studentDTO.getEmail() );
        student.password( studentDTO.getPassword() );
        student.gender( studentDTO.getGender() );
        student.address( addressDTOToAddress( studentDTO.getAddress() ) );
        student.phoneNo( studentDTO.getPhoneNo() );
        student.parentName( studentDTO.getParentName() );

        return student.build();
    }

    protected List<Student> studentDTOListToStudentList(List<StudentDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Student> list1 = new ArrayList<Student>( list.size() );
        for ( StudentDTO studentDTO : list ) {
            list1.add( studentDTOToStudent( studentDTO ) );
        }

        return list1;
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

    protected StudentDTO studentToStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO.StudentDTOBuilder studentDTO = StudentDTO.builder();

        studentDTO.id( student.getId() );
        studentDTO.parentName( student.getParentName() );
        studentDTO.name( student.getName() );
        if ( student.getDob() != null ) {
            studentDTO.dob( DateTimeFormatter.ISO_LOCAL_DATE.format( student.getDob() ) );
        }
        studentDTO.email( student.getEmail() );
        studentDTO.gender( student.getGender() );
        studentDTO.address( addressToAddressDTO( student.getAddress() ) );
        studentDTO.phoneNo( student.getPhoneNo() );
        studentDTO.password( student.getPassword() );

        return studentDTO.build();
    }

    protected List<StudentDTO> studentListToStudentDTOList(List<Student> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentDTO> list1 = new ArrayList<StudentDTO>( list.size() );
        for ( Student student : list ) {
            list1.add( studentToStudentDTO( student ) );
        }

        return list1;
    }
}
