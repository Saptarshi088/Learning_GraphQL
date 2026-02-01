package com.saptarshi.SpringGraphQL.repository;

import com.saptarshi.SpringGraphQL.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
