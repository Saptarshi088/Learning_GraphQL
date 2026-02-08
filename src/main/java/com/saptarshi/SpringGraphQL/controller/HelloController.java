package com.saptarshi.SpringGraphQL.controller;

import com.saptarshi.SpringGraphQL.dto.StudentResponseForRest;
import com.saptarshi.SpringGraphQL.entity.Student;
import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final StudentRepository studentRepository;

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/studs")
    public List<StudentResponseForRest> getAllStudents() {

        return studentRepository
                .findAll()
                .stream()
                .map(s ->
                        new StudentResponseForRest()
                                .builder()
                                .firstName(s.getFirstName())
                                .lastName(s.getLastName())
                                .marks(s.getMarks())
                                .email(s.getEmail())
                                .departmentId(s.getDepartment().getDepartmentId())
                                .build()
                        )
                .toList();
    }
}
