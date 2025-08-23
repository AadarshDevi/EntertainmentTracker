package com.alphag947.backend.entertainment;

import java.time.LocalDate;

import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;

public class Movie extends Entertainment {

    private int duration;
    private String[] animation_companies;

    private String visualDuration;

    public Movie(
            int id,
            EntertainmentType type,
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

    @Override
    public String getDataLine() throws EntertainmentNotFoundException {
        return getType() + mainDelimiter +
                getFranchise() + mainDelimiter +
                getTitle() + mainDelimiter +
                getStatusLine() + mainDelimiter +
                getTagsDataLine() + mainDelimiter +
                getDuration() + mainDelimiter +
                getDate() + mainDelimiter +
                getAnimationCompaniesDataLine();
    }

    private String getAnimationCompaniesDataLine() {
        String animationCompaniesDataLine = "";
        for (int i = 0; i < animation_companies.length; i++) {
            animationCompaniesDataLine += animation_companies[i];
            if (i != animation_companies.length - 1)
                animationCompaniesDataLine += subDelimiter;
        }
        return animationCompaniesDataLine;
    }
}
