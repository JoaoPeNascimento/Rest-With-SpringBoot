package com.joaopenascimento.rest_with_spring_boot.service;

import com.joaopenascimento.rest_with_spring_boot.dto.PersonRequestDTO;
import com.joaopenascimento.rest_with_spring_boot.dto.PersonResponseDTO;
import com.joaopenascimento.rest_with_spring_boot.mapper.PersonMapper;
import com.joaopenascimento.rest_with_spring_boot.model.Person;
import com.joaopenascimento.rest_with_spring_boot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public PersonService(PersonRepository repository){
        this.repository = repository;
    }

    public PersonResponseDTO create(PersonRequestDTO dto) {
        Person entity = repository.save(PersonMapper.toEntity(dto));
        return PersonMapper.toResponseDTO(entity);
    }

    public List<PersonResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(PersonMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PersonResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(PersonMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public PersonResponseDTO update(Long id, PersonRequestDTO dto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not Found"));

        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setAge(dto.getAge());

        return PersonMapper.toResponseDTO(repository.save(person));
    }

    public void delete(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not Found"));

        repository.delete(person);
    }
}
