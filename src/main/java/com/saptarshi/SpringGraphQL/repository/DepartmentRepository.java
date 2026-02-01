package com.saptarshi.SpringGraphQL.repository;

import com.saptarshi.SpringGraphQL.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}