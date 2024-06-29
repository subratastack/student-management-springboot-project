package com.example.school_management_system.bootstrap;

import com.example.school_management_system.entity.Address;
import com.example.school_management_system.entity.Student;
import com.example.school_management_system.repositories.StudentRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Component
@RequiredArgsConstructor
@Profile("test")
public class StudentBootstrapData implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private static final Faker FAKER;
    private static final Integer NO_OF_RECORDS;

    static {
        NO_OF_RECORDS = 20;
        FAKER = Faker.instance(Locale.US);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < NO_OF_RECORDS; i++) {
            generateStudent();
        }
    }

    private void generateStudent() {
        final Date date = FAKER.date().birthday(8, 15);
        final LocalDate DOB = LocalDate.of(date.getYear(), date.getMonth() + 1, date.getDate());
        final String NAME = FAKER.name().fullName();
        final Address ADDRESS = Address.builder()
                .houseNo(FAKER.address().streetAddressNumber())
                .streetName(FAKER.address().streetName())
                .city(FAKER.address().cityName())
                .zipcode(FAKER.address().zipCode())
                .build();

        Student student = Student.builder()
                .name(NAME)
                .dob(DOB)
                .email(FAKER.name().username() + "@email.com")
                .gender(FAKER.demographic().sex())
                .parentName(FAKER.name().fullName())
                .address(ADDRESS)
                .phoneNo(FAKER.phoneNumber().cellPhone())
                .build();

        studentRepository.save(student);
    }
}
