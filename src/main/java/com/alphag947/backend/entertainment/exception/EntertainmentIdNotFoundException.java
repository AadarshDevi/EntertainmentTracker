package com.alphag947.backend.entertainment.exception;

public class EntertainmentIdNotFoundException extends EntertainmentNotFoundException {

    public EntertainmentIdNotFoundException(int id) {
        super("Entertainment with id \"" + id + "\" not found.");
    }

    public EntertainmentIdNotFoundException(String string) {
        super(string);
    }
}
