package com.example.Ticket.Raising.Backendd.model;



public class ClientDTO {
   
    private String cusername;
    private String cemail;
    private String cpassword;
	public ClientDTO(String cusername, String cemail, String cpassword) {
		super();
	
		this.cusername = cusername;
		this.cemail = cemail;
		this.cpassword = cpassword;
	}
	public ClientDTO() {
		super();
		// TODO Auto-generated constructor stub
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

}