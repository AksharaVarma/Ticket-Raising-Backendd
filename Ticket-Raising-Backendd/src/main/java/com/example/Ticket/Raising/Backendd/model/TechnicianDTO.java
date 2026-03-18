package com.example.Ticket.Raising.Backendd.model;



public class TechnicianDTO {
  
    private String techusername;
    private String techemail;
    private String techpassword;
    private String domain;
    public TechnicianDTO(String techusername, String techemail, String techpassword, String domain) {
		super();
		this.techusername = techusername;
		this.techemail = techemail;
		this.techpassword = techpassword;
		this.domain = domain;
	}



	public String getTechpassword() {
		return techpassword;
	}

	public void setTechpassword(String techpassword) {
		this.techpassword = techpassword;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
    