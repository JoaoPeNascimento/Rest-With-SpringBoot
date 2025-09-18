package com.joaopenascimento.rest_with_spring_boot.repository;

import com.joaopenascimento.rest_with_spring_boot.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    void delete(Person person);
    Page<Person> findAllByCompanyId(Long companyId, Pageable pageable);
}
