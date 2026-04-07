package com.alphagnfss.etr3.ui.v1.fxml.exceptions;

public class URLNotFoundException extends Exception {
	public URLNotFoundException(String message) {
		super("URL Not Found: " + message);
	}
}