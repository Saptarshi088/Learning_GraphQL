package com.saptarshi.SpringGraphQL.repository;

import com.saptarshi.SpringGraphQL.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department  d left join fetch d.students")
    List<Department> findAllDepartmentWithStudents();

}