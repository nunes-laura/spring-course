package application.demo.controllers;

import application.demo.dtos.ClientDTO;
import application.demo.models.Clients;
import application.demo.services.ClientsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })

    public ResponseEntity<List<ClientDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( service.findAll());
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })

    public ResponseEntity<ClientDTO> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping(value = "/save",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO c){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(c));
    }

    @PutMapping(value = "/update/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<ClientDTO> update (@PathVariable UUID id,@Valid @RequestBody Clients c){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, c));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
            service.delete(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
