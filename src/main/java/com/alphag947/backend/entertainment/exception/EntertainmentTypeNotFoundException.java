package com.alphag947.backend.entertainment.exception;

import com.alphag947.backend.entertainment.enumeration.EntertainmentType;

public class EntertainmentTypeNotFoundException extends EntertainmentNotFoundException {
    public EntertainmentTypeNotFoundException(EntertainmentType et) {
        super("Entertainment with status \"" + et + "\" not found.");
    }
}
