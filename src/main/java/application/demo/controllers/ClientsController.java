package application.demo.controllers;

import application.demo.models.Clients;
import application.demo.services.ClientsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsService service;

    @GetMapping
    public ResponseEntity<List<Clients>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Clients> create(@Valid @RequestBody Clients c){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(c));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Clients> update (@PathVariable UUID id,@Valid @RequestBody Clients c){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, c));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
