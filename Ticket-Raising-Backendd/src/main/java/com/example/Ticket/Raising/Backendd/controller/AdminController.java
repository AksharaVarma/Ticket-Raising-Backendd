package com.example.Ticket.Raising.Backendd.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ticket.Raising.Backendd.service.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_EMAIL    = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @Autowired private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds,
                                    HttpSession session) {
        if (ADMIN_EMAIL.equals(creds.get("email")) &&
            ADMIN_PASSWORD.equals(creds.get("password"))) {
            session.setAttribute("adminId", 1);
            return ResponseEntity.ok("Admin logged in successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid admin credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Admin logged out successfully");
    }

    @GetMapping("/raisedTickets")
    public ResponseEntity<?> getRaisedTickets(HttpSession session) {
        if (session.getAttribute("adminId") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return adminService.getRaisedTickets();
    }

    @PutMapping("/assignTechnician/{tid}")
    public ResponseEntity<?> assignTechnician(@PathVariable Integer tid,
                                               HttpSession session) {
        if (session.getAttribute("adminId") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return adminService.assignTechnician(tid);
    }

    @GetMapping("/viewAllTickets")
    public ResponseEntity<?> viewAllTickets(HttpSession session) {
        if (session.getAttribute("adminId") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return adminService.viewAllTickets();
    }

    @PutMapping("/respondToClient/{atid}")
    public ResponseEntity<?> respondToClient(@PathVariable Integer atid,
                                              HttpSession session) {
        if (session.getAttribute("adminId") == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Please login first");
        return adminService.respondToClient(atid);
    }
}