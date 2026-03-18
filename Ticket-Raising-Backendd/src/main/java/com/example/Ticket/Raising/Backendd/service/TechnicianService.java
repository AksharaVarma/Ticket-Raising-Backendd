package com.example.Ticket.Raising.Backendd.service;


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
import com.example.Ticket.Raising.Backendd.model.TechnicianDTO;

import jakarta.servlet.http.HttpSession;



@Service
public class TechnicianService {

    @Autowired private TechnicianRepo techRepo;
    @Autowired private BeforeTicketRepo beforeTicketRepo;
    @Autowired private AfterTicketRepo afterTicketRepo;

    public ResponseEntity<?> register(TechnicianDTO techRequest) {
        if (techRepo.findByTechemail(techRequest.getTechemail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }
        Technician tech = new Technician();
        tech.setTechusername(techRequest.getTechusername());
        tech.setTechemail(techRequest.getTechemail());
        tech.setTechpassword(techRequest.getTechpassword());
        tech.setDomain(techRequest.getDomain());
        techRepo.save(tech);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registered Successfully");
    }

    public ResponseEntity<?> login(TechnicianDTO techRequest, HttpSession session) {
        Technician tech = techRepo
                .findByTechemail(techRequest.getTechemail())
                .orElse(null);
        if (tech == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        if (!techRequest.getTechpassword().equals(tech.getTechpassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }
       
        session.setAttribute("techId", tech.getTechid());
        return ResponseEntity.ok("Technician Login successful");
    }

    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
  public ResponseEntity<?> getAssignedTickets(HttpSession session) {
        Integer techId = (Integer) session.getAttribute("techId");
        return ResponseEntity.ok(beforeTicketRepo.findByAssignedTechId(techId));
    }

  public ResponseEntity<?> reportToAdmin(Integer tid, AfterTicket reportRequest,
          HttpSession session) {
Integer techId = (Integer) session.getAttribute("techId");

Optional<BeforeTicket> opt = beforeTicketRepo.findById(tid);
if (opt.isEmpty())
return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("Ticket not found");

BeforeTicket bt = opt.get();
if (!bt.getAssignedTechId().equals(techId))
return ResponseEntity.status(HttpStatus.FORBIDDEN)
.body("Not your ticket");

String status = reportRequest.getStatus();

// RESOLVED -> move to AfterTicket table
if (status.equals("RESOLVED")) {
AfterTicket at = new AfterTicket();
at.setIssuetitle(bt.getIssuetitle());
at.setIssuedate(bt.getIssuedate());
at.setDescription(bt.getDescription());
at.setDomain(bt.getDomain());
at.setClientId(bt.getClientId());
at.setTechId(techId);
at.setStatus(reportRequest.getStatus());
at.setSol(reportRequest.getSol());
at.setSentToClient(false);
afterTicketRepo.save(at);
beforeTicketRepo.deleteById(tid);
return ResponseEntity.ok("Ticket resolved successfully");
}

// INPROGRESS or NOT_RESOLVED -> update status in BeforeTicket table
bt.setStatus(status);
beforeTicketRepo.save(bt);
return ResponseEntity.ok("Ticket status updated to " + status);
}
}