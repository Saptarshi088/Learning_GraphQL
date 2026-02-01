package com.saptarshi.SpringGraphQL.controller;

import com.saptarshi.SpringGraphQL.dto.CreateStudentRequest;
import com.saptarshi.SpringGraphQL.entity.Department;
import com.saptarshi.SpringGraphQL.entity.Student;
import com.saptarshi.SpringGraphQL.repository.DepartmentRepository;
import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/graphql")
@RequiredArgsConstructor
@CrossOrigin
public class GraphQLController {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @QueryMapping()
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @QueryMapping
    public Student getStudentById(@Argument Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @MutationMapping
    public Student createStudent(@Argument("input") CreateStudentRequest request) {
        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .marks(request.getMarks())
                .department(departmentRepository.findById(request.getDepartmentId()).orElseThrow(()-> new RuntimeException("Department with ID : " + request.getDepartmentId() + " not found!")))
                .build();

        return studentRepository.save(student);
    }

    @QueryMapping
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
}
