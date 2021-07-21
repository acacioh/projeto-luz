package br.ong.luz.projetoluz.controller;

import br.ong.luz.projetoluz.model.Person;
import br.ong.luz.projetoluz.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @GetMapping
    public List<Person> getPerson() {
        List<Person> person = personRepository.findAll();
        return person;
    }
}
