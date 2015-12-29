package io.pivotal.cf.cassandra.demo.controllers;


import io.pivotal.cf.cassandra.demo.models.Person;
import io.pivotal.cf.cassandra.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        String id = UUID.randomUUID().toString();
        person.setId(id);
        person = personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseEntity<Person> add(@RequestBody String name) {
        String id = UUID.randomUUID().toString();
        Person person = personRepository.save(new Person( id, "Sharath" , 45));
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

}
