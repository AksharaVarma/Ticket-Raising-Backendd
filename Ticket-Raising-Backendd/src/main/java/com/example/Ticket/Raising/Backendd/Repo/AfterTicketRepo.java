package com.example.Ticket.Raising.Backendd.Repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ticket.Raising.Backendd.model.AfterTicket;

@Repository
public interface AfterTicketRepo extends JpaRepository<AfterTicket, Integer> {
    List<AfterTicket> findByClientIdAndSentToClient(Integer clientId, Boolean sentToClient);
}