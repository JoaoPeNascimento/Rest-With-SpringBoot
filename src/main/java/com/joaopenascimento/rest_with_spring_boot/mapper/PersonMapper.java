package com.joaopenascimento.rest_with_spring_boot.mapper;

import com.joaopenascimento.rest_with_spring_boot.dto.PersonRequestDTO;
import com.joaopenascimento.rest_with_spring_boot.dto.PersonResponseDTO;
import com.joaopenascimento.rest_with_spring_boot.model.Person;

public class PersonMapper {

    public static Person toEntity(PersonRequestDTO dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setAge(dto.getAge());
        return person;
    };

    public static PersonResponseDTO toResponseDTO(Person person){
        return new PersonResponseDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAge(),
                person.getAddress()
        );
    }
}
