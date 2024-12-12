package com.sanmatibanne.StudentManagement.dao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    @NotEmpty(message = "field should not be empty")
    private String firstName;
    @NotEmpty(message = "field should not be empty")
    private String lastName;
    @NotEmpty(message = "field should not be empty")
    @Email
    private String email;
}
