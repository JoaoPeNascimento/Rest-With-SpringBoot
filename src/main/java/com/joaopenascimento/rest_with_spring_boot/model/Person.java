package com.joaopenascimento.rest_with_spring_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 80)
    private String address;

    @Column(nullable = false, length = 10)
    private double salary;

    @Column(nullable = false, length = 20)
    private String position;

    @Column(nullable = false, length = 2)
    private int age;

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(salary, person.salary) && Objects.equals(position, person.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, salary, position, age);
    }
}
