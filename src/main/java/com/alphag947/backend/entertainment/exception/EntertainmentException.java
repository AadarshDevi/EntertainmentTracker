package com.alphag947.backend.entertainment.exception;

import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.entertainment.enumeration.EntertainmentType;

public class EntertainmentException extends Exception {

    public EntertainmentException() {
        super("Entertainment Not Found.");
    }

    public EntertainmentException(int id) {
        super("Entertainment with id \"" + id + "\" not found.");
    }

    public EntertainmentException(EntertainmentStatus es) {
        super("Entertainment with status \"" + es + "\" not found.");
    }

    public EntertainmentException(EntertainmentType et) {
        super("Entertainment with status \"" + et + "\" not found.");
    }
}
