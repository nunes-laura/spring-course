package application.demo.controllers;

import application.demo.models.Clients;
import application.demo.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<Clients> client = service.findById(id);
        if (client.isEmpty()) {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(client.get());
    }

    @PostMapping("/save")
    public ResponseEntity<Clients> create(@RequestBody Clients c){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(c));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update (@PathVariable UUID id, @RequestBody Clients c){
      var client =  service.findById(id);
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found.");
        }
        c.setId(client.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(service.update(c));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        Optional<Clients> client = service.findById(id);
        if (client.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Content not found.");
        }
            service.delete(client.get().getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
