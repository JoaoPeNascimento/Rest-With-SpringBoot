package com.joaopenascimento.rest_with_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private String address;
    private int age;

    public PersonRequestDTO() {
    }

    public PersonRequestDTO(String firstName, String lastName, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }
}
