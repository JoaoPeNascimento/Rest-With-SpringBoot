package com.joaopenascimento.rest_with_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private double salary;
    private String position;
    private int age;

    public PersonResponseDTO() {
    }

    public PersonResponseDTO(Long id, String firstName, String lastName, int age, String address, double salary, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.position = position;
    }
}
