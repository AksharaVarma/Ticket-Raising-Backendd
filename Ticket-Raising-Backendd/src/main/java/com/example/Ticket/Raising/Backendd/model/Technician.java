package com.example.Ticket.Raising.Backendd.model;



import jakarta.persistence.*;

@Entity
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer techid;
    private String techusername;
    private String techemail;
    private String techpassword;
    private String domain;
	
	public Technician() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getTechid() {
		return techid;
	}
	public void setTechid(Integer techid) {
		this.techid = techid;
	}
	public String getTechusername() {
		return techusername;
	}
	public void setTechusername(String techusername) {
		this.techusername = techusername;
	}
	public String getTechemail() {
		return techemail;
	}
	public void setTechemail(String techemail) {
		this.techemail = techemail;
	}
	public String getTechpassword() {
		return techpassword;
	}
	public void setTechpassword(String techpassword) {
		this.techpassword = techpassword;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Technician(Integer techid, String techusername, String techemail, String techpassword, String domain) {
		super();
		this.techid = techid;
		this.techusername = techusername;
		this.techemail = techemail;
		this.techpassword = techpassword;
		this.domain = domain;
	}

}