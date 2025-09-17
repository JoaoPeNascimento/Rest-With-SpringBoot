package com.joaopenascimento.rest_with_spring_boot.service;

import com.joaopenascimento.rest_with_spring_boot.dto.person.PersonRequestDTO;
import com.joaopenascimento.rest_with_spring_boot.dto.person.PersonResponseDTO;
import com.joaopenascimento.rest_with_spring_boot.mapper.PersonMapper;
import com.joaopenascimento.rest_with_spring_boot.model.Person;
import com.joaopenascimento.rest_with_spring_boot.model.User;
import com.joaopenascimento.rest_with_spring_boot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();

        Person entity = PersonMapper.toEntity(dto);
        entity.setCompanyId(userId);

        return PersonMapper.toResponseDTO(repository.save(entity));
    }

    public ResponseEntity<?> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        Long userId = user.getId();

        List<PersonResponseDTO> people = repository.findAllByCompanyId(userId)
                .stream()
                .map(PersonMapper::toResponseDTO)
                .collect(Collectors.toList());

        if (people.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhuma pessoa cadastrada");
        }

        return ResponseEntity.ok(people);
    }

    public PersonResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(PersonMapper::toResponseDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa de id: " + id + " não encontrada."));
    }

    public PersonResponseDTO update(Long id, PersonRequestDTO dto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa de id: " + id + " não encontrada."));

        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAddress(dto.getAddress());
        person.setPosition(dto.getPosition());
        person.setSalary(dto.getSalary());
        person.setAge(dto.getAge());

        return PersonMapper.toResponseDTO(repository.save(person));
    }

    public void delete(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa de id: " + id + " não encontrada."));

        repository.delete(person);
    }
}
