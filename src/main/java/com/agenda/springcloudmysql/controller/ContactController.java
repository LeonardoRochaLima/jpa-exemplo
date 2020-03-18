package com.agenda.springcloudmysql.controller;

import com.agenda.springcloudmysql.model.Contact;
import com.agenda.springcloudmysql.repository.Contact_Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ContactController {
    private Contact_Repository repository;
    public ContactController(Contact_Repository contactRepository) {
        this.repository = contactRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return repository.save(contact);
    }

    @PutMapping
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Contact contact){
        return repository.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Contact updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id){
        return repository.findById(id)
                .map(record -> {
                  repository.deleteById(id);
                  return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
