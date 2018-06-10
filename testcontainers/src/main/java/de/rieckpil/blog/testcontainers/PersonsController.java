package de.rieckpil.blog.testcontainers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonsController {

    private final PersonRepository personRepository;

    public PersonsController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return  personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id) {

        return personRepository.findById(id).orElseThrow(() -> new NoPersonFoundException("Person with id:" + id +
                " not found"));
    }

    @PostMapping
    public Person createNewPerson(@RequestBody Person person) {

        Person newPerson = new Person();
        newPerson.setName(person.getName());

        personRepository.save(newPerson);

        return newPerson;
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable("id") Long id) {

        personRepository.deleteById(id);

    }
}
