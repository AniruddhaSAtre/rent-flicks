package com.rentflicks.error;

public enum ErrorType {
	ValidationError(1);

	private int code;

	ErrorType(final int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
