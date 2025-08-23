package com.alphag947.backend.entertainment.exception;

public class EntertainmentNotFoundException extends Exception {

    public EntertainmentNotFoundException() {
        super("Entertainment Not Found.");
    }

    public EntertainmentNotFoundException(String message) {
        super(message);
    }

}
