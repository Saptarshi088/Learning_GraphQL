package com.saptarshi.SpringGraphQL.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer marks;
}
