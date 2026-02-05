package com.saptarshi.SpringGraphQL.controller;

import com.saptarshi.SpringGraphQL.entity.Student;
import com.saptarshi.SpringGraphQL.repository.DepartmentRepository;
import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.graphql.test.autoconfigure.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@GraphQlTest(GraphQLController.class)
class GraphQLControllerIntegrationTest {

    @Autowired
    GraphQlTester tester;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private DepartmentRepository departmentRepository;


    @Test
    void testGetAllStudents() {


        // language=GraphQL
        String document = """
                   query {
                        getAllStudents{
                            id
                            firstName
                            lastName
                            marks
                        }
                   }
                """;

        tester.document(document)
                .execute()
                .path("getAllStudents")
                .entityList(Student.class);
    }
}