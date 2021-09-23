package it.partec.webappspringdata.exception;

public class CommonException extends Exception {

	public CommonException(Exception e) {
		super(e);
	}
	
	public CommonException(String message) {
		super(message);
	}	
}
