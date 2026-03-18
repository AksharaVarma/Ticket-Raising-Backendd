package com.example.Ticket.Raising.Backendd.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AfterTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    private String issuetitle;
    private LocalDate issuedate;
    private String description;
    private String domain;
    private String status;
    private String sol;
    private Integer clientId;
    private Integer techId;
    private Boolean sentToClient;
    public Boolean getSentToClient() {
		return sentToClient;
	}

	public void setSentToClient(Boolean sentToClient) {
		this.sentToClient = sentToClient;
	}

	public AfterTicket(Integer tid, String issuetitle, LocalDate issuedate, String description, String domain,
			String status, String sol, Integer clientId, Integer techId, Boolean sentToClient) {
		super();
		this.tid = tid;
		this.issuetitle = issuetitle;
		this.issuedate = issuedate;
		this.description = description;
		this.domain = domain;
		this.status = status;
		this.sol = sol;
		this.clientId = clientId;
		this.techId = techId;
		this.sentToClient = sentToClient;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getIssuetitle() {
		return issuetitle;
	}

	public void setIssuetitle(String issuetitle) {
		this.issuetitle = issuetitle;
	}

	public LocalDate getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(LocalDate issuedate) {
		this.issuedate = issuedate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSol() {
		return sol;
	}

	public void setSol(String sol) {
		this.sol = sol;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getTechId() {
		return techId;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	

	

    public AfterTicket() {
    	
    }

}