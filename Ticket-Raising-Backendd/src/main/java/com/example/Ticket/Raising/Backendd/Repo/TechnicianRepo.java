package com.example.Ticket.Raising.Backendd.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ticket.Raising.Backendd.model.Technician;

@Repository
public interface TechnicianRepo extends JpaRepository<Technician, Integer> {
    Optional<Technician> findByTechemail(String techemail);
    List<Technician> findByDomain(String domain);
}