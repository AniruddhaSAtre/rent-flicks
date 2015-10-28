package com.rentflicks.error;

public class ErrorObject {

	private String		message;
	private ErrorType	errorType;

	public ErrorObject(String message, ErrorType errorType) {
		super();
		this.message = message;
		this.errorType = errorType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

}
