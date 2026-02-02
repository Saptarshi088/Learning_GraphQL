package com.saptarshi.SpringGraphQL.controller;

import com.saptarshi.SpringGraphQL.dto.CreateStudentRequest;
import com.saptarshi.SpringGraphQL.dto.UpdateStudentRequest;
import com.saptarshi.SpringGraphQL.entity.Department;
import com.saptarshi.SpringGraphQL.entity.Student;
import com.saptarshi.SpringGraphQL.repository.DepartmentRepository;
import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import jakarta.transaction.Transactional;
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

    @QueryMapping() // GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @QueryMapping
    public Student getStudentById(@Argument Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @MutationMapping // PostMapping
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
//        return departmentRepository.findAll(); // This creates N+1 problem
        return departmentRepository.findAllDepartmentWithStudents(); // This solves the N+1 problem
    }


    @MutationMapping
    public Department createDepartment(@Argument String departmentName){
        Department department = new Department();
        department.setDepartmentName(departmentName);
        return departmentRepository.save(department);

    }

    @MutationMapping // PutMapping
    public Student updateStudent(@Argument(name = "input") UpdateStudentRequest request) {

        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (request.getFirstName() != null)
            student.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            student.setLastName(request.getLastName());

        if (request.getMarks() != null)
            student.setMarks(request.getMarks());

        return studentRepository.save(student);
    }

}
