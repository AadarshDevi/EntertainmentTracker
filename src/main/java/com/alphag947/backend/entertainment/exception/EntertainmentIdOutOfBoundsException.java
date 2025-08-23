package com.alphag947.backend.entertainment.exception;

public class EntertainmentIdOutOfBoundsException extends EntertainmentNotFoundException {

    public EntertainmentIdOutOfBoundsException(int id) {
        super("Entertainment Id \"" + id + "\" is out of Bounds");
    }

}
