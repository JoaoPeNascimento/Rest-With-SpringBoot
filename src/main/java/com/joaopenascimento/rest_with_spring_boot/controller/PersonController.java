package com.joaopenascimento.rest_with_spring_boot.controller;

import com.joaopenascimento.rest_with_spring_boot.dto.person.PersonRequestDTO;
import com.joaopenascimento.rest_with_spring_boot.dto.person.PersonResponseDTO;
import com.joaopenascimento.rest_with_spring_boot.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/person", produces = "application/json")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public PersonResponseDTO create(@RequestBody PersonRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PersonResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public PersonResponseDTO update(@RequestBody PersonRequestDTO dto, @PathVariable Long id) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
