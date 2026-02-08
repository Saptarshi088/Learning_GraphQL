package com.saptarshi.SpringGraphQL.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StudentResponseForRest {
    private String firstName;
    private String lastName;
    private double marks;
    private String email;
    private Long departmentId;
}
