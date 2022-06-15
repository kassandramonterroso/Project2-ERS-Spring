package com.ersspring.exceptions;

public class InvalidLogin extends Exception{
	String message;
	
	public InvalidLogin(String message) {
		this.message = message;
	}

	public String getMessage() {
		return "Invalid Username or password";
	}

}
