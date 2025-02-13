package com.example.school_management_system.services;

import com.example.school_management_system.dto.StudentDTO;
import com.example.school_management_system.mappers.AddressMapper;
import com.example.school_management_system.mappers.StudentMapper;
import com.example.school_management_system.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AddressMapper addressMapper;

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        return studentMapper.studentToStudentDto(
                studentRepository.save(studentMapper.studentDtoToStudent(dto))
        );
    }

    @Override
    public Optional<StudentDTO> getStudentById(UUID id) {
        System.out.println(id);
        return Optional.ofNullable(studentMapper.studentToStudentDto(
           studentRepository.findById(id).orElse(null)
        ));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::studentToStudentDto)
                .toList();
    }

    @Override
    public Optional<StudentDTO> updateStudentById(UUID id, StudentDTO dto) {
        AtomicReference<Optional<StudentDTO>> atomicReference = new AtomicReference<>();

        studentRepository.findById(id).ifPresentOrElse(studentFound -> {
            studentFound.setName(dto.getName());
            studentFound.setDob(dto.getDob() != null ? LocalDate.parse(dto.getDob()) : null);
            studentFound.setEmail(dto.getEmail());
            studentFound.setGender(dto.getGender());
            studentFound.setAddress(addressMapper.addressDtoToAddress(dto.getAddress()));
            studentFound.setParentName(dto.getParentName());
            studentFound.setPhoneNo(dto.getPhoneNo());
            atomicReference.set(Optional.ofNullable(
                    studentMapper.studentToStudentDto(studentRepository.save(studentFound))
            ));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public Boolean deleteStudentById(UUID studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return true;
        }

        return false;
    }
}
