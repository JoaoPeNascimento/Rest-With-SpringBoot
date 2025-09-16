package com.joaopenascimento.rest_with_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private String address;
    private double salary;
    private String position;
    private int age;

    public PersonRequestDTO() {
    }

    public PersonRequestDTO(String firstName, String lastName, int age, String address, double salary, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }
}
