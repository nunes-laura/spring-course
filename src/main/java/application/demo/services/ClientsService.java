package application.demo.services;

import application.demo.models.Clients;
import application.demo.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    public List<Clients> findAll(){
        return repository.findAll();
    }

    public Optional<Clients> findById(@PathVariable UUID id){
        return repository.findById(id);
    }

    public Clients create(@RequestBody Clients c){
        return repository.save(c);
    }

    public Clients update(@RequestBody Clients c){ return repository.save(c);}

    public void delete(@PathVariable UUID id){
        repository.deleteById(id);
    }


}
