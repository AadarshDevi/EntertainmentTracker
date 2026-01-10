package com.alphagnfss.etr3.backend.data;

public class EntertainmentDoesNotExistException extends Throwable {
	public EntertainmentDoesNotExistException(int id) {
		super("Entertainment with id \"" + id + "\" does not exist.");
	}
}
