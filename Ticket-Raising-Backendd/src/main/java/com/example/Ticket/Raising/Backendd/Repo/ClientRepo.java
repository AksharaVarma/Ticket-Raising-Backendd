package com.example.Ticket.Raising.Backendd.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ticket.Raising.Backendd.model.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findByCemail(String cemail);
}
