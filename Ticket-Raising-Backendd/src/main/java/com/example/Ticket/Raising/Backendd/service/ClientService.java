package com.example.Ticket.Raising.Backendd.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Ticket.Raising.Backendd.Repo.AfterTicketRepo;
import com.example.Ticket.Raising.Backendd.Repo.BeforeTicketRepo;
import com.example.Ticket.Raising.Backendd.Repo.ClientRepo;
import com.example.Ticket.Raising.Backendd.model.AfterTicket;
import com.example.Ticket.Raising.Backendd.model.BeforeTicket;
import com.example.Ticket.Raising.Backendd.model.Client;
import com.example.Ticket.Raising.Backendd.model.ClientDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class ClientService {

    @Autowired private ClientRepo clientRepo;
    @Autowired private BeforeTicketRepo beforeTicketRepo;
    @Autowired private AfterTicketRepo afterTicketRepo;

    public ResponseEntity<?> register(ClientDTO clientRequest) {
        if (clientRepo.findByCemail(clientRequest.getCemail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }
        Client client = new Client();
        client.setCusername(clientRequest.getCusername());
        client.setCemail(clientRequest.getCemail());
        client.setCpassword(clientRequest.getCpassword());
        clientRepo.save(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registered Successfully");
    }

    public ResponseEntity<?> login(ClientDTO clientRequest, HttpSession session) {
        Client client = clientRepo
                .findByCemail(clientRequest.getCemail())
                .orElse(null);
        if (client == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
        if (!clientRequest.getCpassword().equals(client.getCpassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }
        session.setAttribute("client", client);
        return ResponseEntity.ok("Client Login successful");
    }

    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    public ResponseEntity<?> raiseTicket(BeforeTicket ticket, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        ticket.setClientId(client.getCid());
        ticket.setIssuedate(LocalDate.now());
        ticket.setStatus("PENDING");
        return ResponseEntity.ok(beforeTicketRepo.save(ticket));
    }

    public ResponseEntity<?> getMyTickets(HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        List<BeforeTicket> pending = beforeTicketRepo.findByClientId(client.getCid());
        List<AfterTicket> resolved = afterTicketRepo
                .findByClientIdAndSentToClient(client.getCid(), true);
        Map<String, Object> result = new HashMap<>();
        result.put("pendingTickets", pending);
        result.put("resolvedTickets", resolved);
        return ResponseEntity.ok(result);
    }
}