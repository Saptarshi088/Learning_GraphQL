package com.saptarshi.SpringGraphQL.dto;

import com.saptarshi.SpringGraphQL.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {
    @NotEmpty(message = "Firstname can not be empty")
    private String firstName;

    private String lastName;

    private Double marks;

    @NotNull(message = "must provide a department id")
    private Long departmentId;

    @NotBlank(message = "email must not be empty")
    @Email
    @Size(max = 100, message = "email must be within 100 character ")
    private String email;

    @NotEmpty(message = "password must not be empty")
    @Size(min=4,max = 32,message = "password must be within 6 to 32 characters long(both inclusive)")
    private String password;

    @NotNull
    private Role role;

    public CreateStudentRequest(String ada, String lovelace, double marks, long departmentId) {
    }
}
