package com.alphag947.backend.entertainment.enumeration;

public enum EntertainmentStatus {
    NULL, // 0
    COMPLETED, // 1
    RELEASED, // 2
    UPCOMING, // 3
    SPECIAL, // 4
    PILOT, // 5
    FAVORITE, // 6
    ONGOING,; // 9

    public static EntertainmentStatus resolve(String string) {
        return EntertainmentStatus.NULL;
    }
}