package com.example.studentbackend.dtos;

public record StudentResponse(
    long id,
    String name,
    String email,
    String cpf,
    String location

) {
}
