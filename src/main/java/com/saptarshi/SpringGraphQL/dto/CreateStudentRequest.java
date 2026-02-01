package com.saptarshi.SpringGraphQL.dto;

import com.saptarshi.SpringGraphQL.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private Double marks;
    private Long departmentId;
}
