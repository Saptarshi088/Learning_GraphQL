package com.saptarshi.SpringGraphQL.dto;

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
}
