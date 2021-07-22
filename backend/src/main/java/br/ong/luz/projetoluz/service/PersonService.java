package br.ong.luz.projetoluz.service;

import br.ong.luz.projetoluz.exception.NotFoundException;
import br.ong.luz.projetoluz.model.Person;
import br.ong.luz.projetoluz.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person create(String name) {
        Person p = new Person();
        p.setName(name);
        Person personSaved = personRepository.save(p);
        return personSaved;
    }

    public Person findById(Integer id) {
        Person person = getValidPerson(id);
        return person;
    }

    private Person getValidPerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException());

        return person;
    }

    public Person update(Person person) {
        getValidPerson(person.getId());

        return personRepository.save(person);
    }

    public void delete(Integer id) {
        Person person = getValidPerson(id);

        personRepository.delete(person);
    }

    public Page<Person> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return personRepository.findAll(pageRequest);
    }
}

