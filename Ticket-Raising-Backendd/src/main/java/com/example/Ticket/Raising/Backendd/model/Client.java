package com.example.Ticket.Raising.Backendd.model;


import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String cusername;
    private String cemail;
    private String cpassword;

    public Client(Integer cid, String cusername, String cemail, String cpassword) {
		super();
		this.cid = cid;
		this.cusername = cusername;
		this.cemail = cemail;
		this.cpassword = cpassword;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public Client() {}

}