package com.example.Ticket.Raising.Backendd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ticket.Raising.Backendd.model.AfterTicket;
import com.example.Ticket.Raising.Backendd.model.TechnicianDTO;
import com.example.Ticket.Raising.Backendd.service.TechnicianService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/technician")
public class TechnicianController {

    @Autowired private TechnicianService techService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TechnicianDTO techRequest) {
        return techService.register(techRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TechnicianDTO techRequest,
                                    HttpSession session) {
        return techService.login(techRequest, session);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        return techService.logout(session);
    }

    @GetMapping("/viewTickets")
    public ResponseEntity<?> viewTickets(HttpSession session) {
        if (session.getAttribute("techId") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return techService.getAssignedTickets(session);
    }

    @PostMapping("/report/{tid}")
    public ResponseEntity<?> reportToClient(@PathVariable Integer tid,
                                             @RequestBody AfterTicket reportRequest,
                                             HttpSession session) {
        if (session.getAttribute("techId") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return techService.reportToAdmin(tid, reportRequest, session);
    }
}
