package com.example.Ticket.Raising.Backendd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ticket.Raising.Backendd.model.BeforeTicket;
import com.example.Ticket.Raising.Backendd.model.ClientDTO;
import com.example.Ticket.Raising.Backendd.service.ClientService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientDTO clientRequest) {
        return clientService.register(clientRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClientDTO clientRequest,
                                    HttpSession session) {
        return clientService.login(clientRequest, session);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        return clientService.logout(session);
    }

    @PostMapping("/raiseTicket")
    public ResponseEntity<?> raiseTicket(@RequestBody BeforeTicket ticket,
                                          HttpSession session) {
        if (session.getAttribute("client") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return clientService.raiseTicket(ticket, session);
    }

    @GetMapping("/myTickets")
    public ResponseEntity<?> myTickets(HttpSession session) {
        if (session.getAttribute("client") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return clientService.getMyTickets(session);
    }
}