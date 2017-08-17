package com.johnny.exception;

public class FileUploadException extends RuntimeException{
	
	private static final long serialVersionUID = -7001174338475158956L;

	public FileUploadException(String message) {
		super(message);
	}
	
	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
