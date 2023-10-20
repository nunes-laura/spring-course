package application.demo.repositories;

import application.demo.models.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, UUID> {


}
