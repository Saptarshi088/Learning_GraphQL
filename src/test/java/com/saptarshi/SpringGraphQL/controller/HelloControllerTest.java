package com.saptarshi.SpringGraphQL.controller;

import com.saptarshi.SpringGraphQL.entity.Student;
import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private HelloController controller;

    @Test
    void hello_returnsGreeting() {
        assertEquals("Hello World", controller.hello());
    }

    @Test
    void getAllStudents_returnsRepositoryList() {
        List<Student> students = List.of(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = controller.getAllStudents();

        assertSame(students, result);
        verify(studentRepository).findAll();
    }
}
