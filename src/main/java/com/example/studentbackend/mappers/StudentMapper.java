package com.example.studentbackend.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.studentbackend.dtos.StudentRequest;
import com.example.studentbackend.dtos.StudentResponse;
import com.example.studentbackend.entities.Student;

public class StudentMapper {
    public static Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.name());
        student.setEmail(request.email());
        student.setCpf(request.cpf());
        student.setLocation(request.location());
        return student;
    }

    public static StudentResponse toDTO(Student student) {
        return new StudentResponse(student.getId(), student.getName(), student.getEmail(), student.getCpf(),
                student.getLocation());
    }

    public static List<StudentResponse> toDTOList(List<Student> students) {
        return students.stream()
                .map(StudentMapper::toDTO) //Mapeia cada Student para StudentResponse.
                .collect(Collectors.toList()); //Coleta os resultados em uma lista.
    }
}

