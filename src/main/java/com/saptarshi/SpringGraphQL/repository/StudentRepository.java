package com.saptarshi.SpringGraphQL.repository;

import com.saptarshi.SpringGraphQL.dto.StudentResponseForRest;
import com.saptarshi.SpringGraphQL.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByFirstName(String name);

    Student findByEmail(String email);


    @Query("""
            select new com.saptarshi.SpringGraphQL.dto.StudentResponseForRest(
                s.firstName,
                s.lastName,
                s.marks,
                s.email,
                d.departmentId
            )
            from Student s
            left join s.department d
            """)
    List<StudentResponseForRest> findAllStudentResponses();


}
