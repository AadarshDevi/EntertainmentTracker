package com.alphag947.backend.entertainment;

import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class Movie extends Entertainment {

    @Getter
    @Setter
    private int duration;

    @Getter
    private String[] animationCompanies;

    @Getter
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
            String[] animationCompanies

    ) {
        super(id, type, franchise, title, statuses, tags, date);

            this.duration = duration;
            this.animationCompanies = animationCompanies;

        visualDuration = setVisualDuration();
    }

    public String setVisualDuration() {
        if (duration == 0)
            return "Unknown";
        return duration + " min";
    }

//    @Override
//    public String getDataLine() throws EntertainmentNotFoundException {
//        return getType() + mainDelimiter +
//                getFranchise() + mainDelimiter +
//                getTitle() + mainDelimiter +
//                getStatusLine() + mainDelimiter +
//                getTagsDataLine() + mainDelimiter +
//                getDuration() + mainDelimiter +
//                getDate() + mainDelimiter +
//                getAnimationCompaniesDataLine();
//    }

    private String getAnimationCompaniesDataLine() {
        StringBuilder animationCompaniesDataLine = new StringBuilder();
        for (int i = 0; i < animationCompanies.length; i++) {
            animationCompaniesDataLine.append(animationCompanies[i]);
            if (i != animationCompanies.length - 1)
                animationCompaniesDataLine.append(subDelimiter);
        }
        return animationCompaniesDataLine.toString();
    }
}
