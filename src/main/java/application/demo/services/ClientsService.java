package application.demo.services;

import application.demo.exceptions.BusinessException;
import application.demo.models.Clients;
import application.demo.repositories.ClientsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    public List<Clients> findAll(){
        return repository.findAll();
    }

    public Clients findById(@PathVariable UUID id){
        return repository.findById(id).orElseThrow(() -> new BusinessException("Content not found!"));
    }

    public Clients create(@Valid @RequestBody Clients c){return repository.save(c);}

    public Clients update(@PathVariable UUID id, @Valid @RequestBody Clients c){
        repository.findById(id).orElseThrow(() -> new BusinessException("Content not found!"));;
        c.setId(id);
        return repository.save(c);}

    public void delete(@PathVariable UUID id){
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new BusinessException("Content not found!")));
    }


}
