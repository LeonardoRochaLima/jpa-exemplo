package com.agenda.springcloudmysql.controller;

import com.agenda.springcloudmysql.model.Groups;
import com.agenda.springcloudmysql.repository.Groups_Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GroupsController {
    private Groups_Repository repository;
    GroupsController(Groups_Repository groups_repository) { this.repository = groups_repository;}

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
    public Groups create(@RequestBody Groups groups){
        return repository.save(groups);
    }

    @PutMapping
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Groups groups){
        return repository.findById(id)
                .map(record -> {
                    record.setDescription(groups.getDescription());
                    Groups updated = repository.save(record);
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
