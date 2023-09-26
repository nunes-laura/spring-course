package application.demo.services;

import application.demo.controllers.ClientsController;
import application.demo.dtos.ClientDTO;
import application.demo.exceptions.BusinessException;
import application.demo.models.Clients;
import application.demo.repositories.ClientsRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<ClientDTO> findAll(){
        var clients = repository.findAll();
        var response = clients.stream().map(client -> mapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
        return response;
    }

    public ClientDTO findById (@PathVariable UUID id){
    Clients client = repository.findById(id).get();
    ClientDTO response =  mapper.map(client, ClientDTO.class);
    response.add(linkTo(methodOn(ClientsController.class).findById(id)).withSelfRel());
        return response;
    }


    public ClientDTO create (@Valid @RequestBody ClientDTO c){
     var model = mapper.map(c, Clients.class);
     var client = repository.save(model);
     return c;}

    public ClientDTO update(@PathVariable UUID id, @Valid @RequestBody Clients c) {
        repository.findById(id).orElseThrow(() -> new BusinessException("Content not found!"));
        c.setId(id);
        var clientUpdated = repository.save(c);
        return mapper.map(c, ClientDTO.class);
    }

    public void delete(@PathVariable UUID id){
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new BusinessException("Content not found!")));
    }


}
