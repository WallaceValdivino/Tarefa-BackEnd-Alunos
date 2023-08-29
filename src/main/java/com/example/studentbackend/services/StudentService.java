package com.example.studentbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentbackend.dtos.StudentRequest;
import com.example.studentbackend.dtos.StudentResponse;
import com.example.studentbackend.entities.Student;
import com.example.studentbackend.mappers.StudentMapper;
import com.example.studentbackend.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

   @Autowired
   private StudentRepository repository;

   public List<Student> getStudents() {
      return this.repository.findAll();
   }

   public Student getStudent(long id) {
      return this.repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found"));
   }

   public void deleteStudentById(long id) {
      // verificando se existe um produto(entidade) com o id passado
      // se existir, ele exclui o produto
      if (this.repository.existsById(id)) {
         this.repository.deleteById(id);
      }
      // se não, ele lança uma excessão
      else {
         throw new EntityNotFoundException("Student not found");

      }

   }

   public StudentResponse save(StudentRequest student) {
    var entity =  this.repository.save(StudentMapper.toEntity(student));
    return StudentMapper.toDTO(entity);
   }

   public void update(long id, Student student) {
      try {
         var updateStudent = this.repository.getReferenceById(id);
         updateStudent.setName(student.getName());
         updateStudent.setEmail(student.getEmail());
         updateStudent.setCpf(student.getCpf());
         updateStudent.setLocation(student.getLocation());

         this.repository.save(updateStudent);
      } catch (EntityNotFoundException e) {
         throw new EntityNotFoundException("Student not found");
      }

   }

}
