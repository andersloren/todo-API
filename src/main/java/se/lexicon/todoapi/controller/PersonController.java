package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.domain.dto.PersonDTOForm;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.service.PersonService;

import java.util.List;

@RequestMapping("/api/v1/persons")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<PersonDTOView> doCreate(@RequestBody PersonDTOForm personDTOForm) {

        PersonDTOView responseBody = personService.create(personDTOForm, "test.test@test.com");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

        // TODO: 06/11/2023 do this
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonDTOView>> doGetAll() {
        List<PersonDTOView> responseBody = personService.getAll();
        return ResponseEntity.ok().body(responseBody);
    }
}
