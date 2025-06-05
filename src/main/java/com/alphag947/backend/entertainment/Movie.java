package com.alphag947.backend.entertainment;

import java.time.LocalDate;

public class Movie extends Entertainment {

    private int duration;
    private String[] animation_companies;

    private String visualDuration;

    public Movie(
            int id,
            String type,
            String franchise,
            String title,
            String[] statuses,
            String[] tags,
            int duration,
            LocalDate date,
            String[] animation_companies

    ) {
        super(id, type, franchise, title, statuses, tags, date);

        this.duration = duration;
        this.animation_companies = animation_companies;

        visualDuration = setVisualDuration();
    }

    public int getDuration() {
        return duration;
    }

    public String[] getAnimationCompanies() {
        return animation_companies;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String setVisualDuration() {
        if (duration == 0)
            return "Unknown";

        return duration + " min";
    }

    public String getVisualDuration() {
        return visualDuration;
    }

}
