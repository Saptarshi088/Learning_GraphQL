package com.saptarshi.SpringGraphQL.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class loginRequest {
    private String email;
    private String password;
}
