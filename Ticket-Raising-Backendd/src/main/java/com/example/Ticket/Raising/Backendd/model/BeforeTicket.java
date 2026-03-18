package com.example.Ticket.Raising.Backendd.model;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class BeforeTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    private String issuetitle;
    private LocalDate issuedate;
    private String description;
    private String domain;
    private String status;
    private Integer assignedTechId;
    private Integer clientId;

	public BeforeTicket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BeforeTicket(Integer tid, String issuetitle, LocalDate issuedate, String description, String domain,
			String status, Integer assignedTechId, Integer clientId) {
		super();
		this.tid = tid;
		this.issuetitle = issuetitle;
		this.issuedate = issuedate;
		this.description = description;
		this.domain = domain;
		this.status = status;
		this.assignedTechId = assignedTechId;
		this.clientId = clientId;
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
	public Integer getAssignedTechId() {
		return assignedTechId;
	}
	public void setAssignedTechId(Integer assignedTechId) {
		this.assignedTechId = assignedTechId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

}

   