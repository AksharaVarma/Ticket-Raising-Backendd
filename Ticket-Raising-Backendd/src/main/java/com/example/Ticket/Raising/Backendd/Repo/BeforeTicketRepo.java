package com.example.Ticket.Raising.Backendd.Repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ticket.Raising.Backendd.model.BeforeTicket;

@Repository
public interface BeforeTicketRepo extends JpaRepository<BeforeTicket, Integer> {
    List<BeforeTicket> findByClientId(Integer clientId);
    List<BeforeTicket> findByAssignedTechId(Integer techId);
    List<BeforeTicket> findByAssignedTechIdIsNull();
 
}