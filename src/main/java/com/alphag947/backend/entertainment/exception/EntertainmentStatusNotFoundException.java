package com.alphag947.backend.entertainment.exception;

import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;

public class EntertainmentStatusNotFoundException extends EntertainmentNotFoundException {

    public EntertainmentStatusNotFoundException(EntertainmentStatus es) {
        super("Entertainment with status \"" + es + "\" not found.");
    }

    public EntertainmentStatusNotFoundException(int status) {
        super("Entertainment with status number \"" + status + "\" not found.");
    }

}
