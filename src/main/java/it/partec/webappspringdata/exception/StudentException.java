package it.partec.webappspringdata.exception;

public class StudentException extends Exception {

	public StudentException(Exception e) {
		super(e);
	}
	
	public StudentException(String message) {
		super(message);
	}	
}
