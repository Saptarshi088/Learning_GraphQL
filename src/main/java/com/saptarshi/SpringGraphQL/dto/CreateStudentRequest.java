package com.saptarshi.SpringGraphQL.dto;

import com.saptarshi.SpringGraphQL.entity.Role;
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
    private String email;
    private String password;
    private Role role;

    public CreateStudentRequest(String ada, String lovelace, double marks, long departmentId) {
    }
}
