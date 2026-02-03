package com.saptarshi.SpringGraphQL.controller;

import com.saptarshi.SpringGraphQL.dto.CreateStudentRequest;
import com.saptarshi.SpringGraphQL.dto.UpdateStudentRequest;
import com.saptarshi.SpringGraphQL.entity.Department;
import com.saptarshi.SpringGraphQL.entity.Student;
import com.saptarshi.SpringGraphQL.repository.DepartmentRepository;
import com.saptarshi.SpringGraphQL.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GraphQLControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private GraphQLController controller;

    @Test
    void getAllStudents_returnsRepositoryList() {
        List<Student> students = List.of(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = controller.getAllStudents();

        assertSame(students, result);
        verify(studentRepository).findAll();
    }

    @Test
    void getStudentById_throwsWhenMissing() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> controller.getStudentById(99L));

        assertEquals("Student not found", ex.getMessage());
    }

    @Test
    void createStudent_savesWithDepartment() {
        Department department = new Department();
        department.setDepartmentId(10L);
        department.setDepartmentName("CSE");

        CreateStudentRequest request = new CreateStudentRequest("Ada", "Lovelace", 98.5, 10L);

        when(departmentRepository.findById(10L)).thenReturn(Optional.of(department));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student saved = controller.createStudent(request);

        assertEquals("Ada", saved.getFirstName());
        assertEquals("Lovelace", saved.getLastName());
        assertEquals(98.5, saved.getMarks());
        assertSame(department, saved.getDepartment());

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        assertSame(department, captor.getValue().getDepartment());
    }

    @Test
    void updateStudent_updatesOnlyProvidedFields() {
        Student existing = new Student();
        existing.setId(7L);
        existing.setFirstName("Alan");
        existing.setLastName("Turing");
        existing.setMarks(75.0);

        UpdateStudentRequest request = new UpdateStudentRequest(7L, null, "Mathison", null);

        when(studentRepository.findById(7L)).thenReturn(Optional.of(existing));
        when(studentRepository.save(existing)).thenReturn(existing);

        Student saved = controller.updateStudent(request);

        assertEquals("Alan", saved.getFirstName());
        assertEquals("Mathison", saved.getLastName());
        assertEquals(75.0, saved.getMarks());
        verify(studentRepository).save(existing);
    }

    @Test
    void deleteStudent_deletesAndReturnsEntity() {
        Student existing = new Student();
        existing.setId(3L);

        when(studentRepository.findById(3L)).thenReturn(Optional.of(existing));

        Student deleted = controller.deleteStudent(3L);

        assertSame(existing, deleted);
        verify(studentRepository).delete(existing);
    }

    @Test
    void getDepartments_returnsRepositoryList() {
        List<Department> departments = List.of(new Department());
        when(departmentRepository.findAllDepartmentWithStudents()).thenReturn(departments);

        List<Department> result = controller.getDepartments();

        assertSame(departments, result);
        verify(departmentRepository).findAllDepartmentWithStudents();
    }

    @Test
    void createDepartment_persistsName() {
        when(departmentRepository.save(any(Department.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Department saved = controller.createDepartment("EEE");

        assertEquals("EEE", saved.getDepartmentName());
        verify(departmentRepository).save(any(Department.class));
    }
}
