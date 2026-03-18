package com.example.Ticket.Raising.Backendd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Ticket.Raising.Backendd.Repo.AfterTicketRepo;
import com.example.Ticket.Raising.Backendd.Repo.BeforeTicketRepo;
import com.example.Ticket.Raising.Backendd.Repo.TechnicianRepo;
import com.example.Ticket.Raising.Backendd.model.AfterTicket;
import com.example.Ticket.Raising.Backendd.model.BeforeTicket;
import com.example.Ticket.Raising.Backendd.model.Technician;

@Service
public class AdminService {

    @Autowired private BeforeTicketRepo beforeTicketRepo;
    @Autowired private AfterTicketRepo afterTicketRepo;
    @Autowired private TechnicianRepo techRepo;

    public ResponseEntity<?> getRaisedTickets() {
        return ResponseEntity.ok(beforeTicketRepo.findByAssignedTechIdIsNull());
    }

    public ResponseEntity<?> assignTechnician(Integer tid) {

        Optional<BeforeTicket> opt = beforeTicketRepo.findById(tid);
        if (opt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found");
//gets complete beforetkt obj
        BeforeTicket ticket = opt.get();
        // finds all technicians using domain from tech' table)
        String domain = ticket.getDomain();
        List<Technician> techs = techRepo.findByDomain(domain);
        if (techs.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No technician available for domain: " + domain);

        // pick first technician of that domain
        Technician assigned = techs.get(0);
        //  assign only THIS ticket
        ticket.setAssignedTechId(assigned.getTechid());
        ticket.setStatus("ASSIGNED");
        beforeTicketRepo.save(ticket);
        
        
        return ResponseEntity.ok("Technician assigned successfully");
    }
   
    public ResponseEntity<?> viewAllTickets() {
        Map<String, Object> result = new HashMap<>();
        result.put("activeTickets", beforeTicketRepo.findAll());
        result.put("resolvedTickets", afterTicketRepo.findAll());
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> respondToClient(Integer atid) {
        Optional<AfterTicket> opt = afterTicketRepo.findById(atid);
        if (opt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket not found");

        AfterTicket at = opt.get();
        at.setSentToClient(true);
        afterTicketRepo.save(at);
        return ResponseEntity.ok("Response sent to client successfully");
    }
}