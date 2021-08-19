package it.partec.webappspringdata.exception;

public class ClassException extends Exception {

	public ClassException(Exception e) {
		super(e);
	}
	
	public ClassException(String message) {
		super(message);
	}
}
