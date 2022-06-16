package com.ersspring.exceptions;

public class InvalidLoginException extends Exception{
	String message;
	
	public InvalidLoginException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return "Invalid Username or password";
	}

}
