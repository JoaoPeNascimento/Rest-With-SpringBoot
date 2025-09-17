package com.joaopenascimento.rest_with_spring_boot.controller;

import com.joaopenascimento.rest_with_spring_boot.dto.person.PersonRequestDTO;
import com.joaopenascimento.rest_with_spring_boot.dto.person.PersonResponseDTO;
import com.joaopenascimento.rest_with_spring_boot.service.PersonService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PersonResponseDTO person = service.findById(id);
        return ResponseEntity.ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PersonRequestDTO dto, @PathVariable Long id) {
        PersonResponseDTO updatedPerson = service.update(id, dto);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Pessoa deletada com sucesso!");
    }
}
