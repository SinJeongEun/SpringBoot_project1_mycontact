package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.repository.PersonRepository;
import com.fastcampus.javaallinone.project3.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/{id}")
    //@RequestMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id){
        return  personService.getPerson(id);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  //성공일 경우 201번
    public  void postPerson(@RequestBody Person person){
        personService.put(person);

        log.info("person -> {}",personRepository.findAll());
    }
    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.modify(id,personDto);

        log.info("person -> {}" , personRepository.findAll());

    }

    @PatchMapping ("/{id}")
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id,name);

        log.info("person -> {}" , personRepository.findAll());

    }
}
