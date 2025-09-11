package com.alphag947.backend.entertainment;

import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Episode extends Entertainment {

    private int seasonID;
    private int episodeNum;
    private int duration;

    public Episode(
            int id, // episode id
            EntertainmentType type, // episode type

            int episodeNum,
            String episodeTitle, // episode title

            String[] status, // status
            String[] tags, // tags

            int duration,
            LocalDate date, // date
            int seasonID

    ) {
        super(id, type, null, episodeTitle, status, tags, date);
        this.seasonID = seasonID;
        this.episodeNum = episodeNum;
        this.duration = duration;

        this.stageName = createStageName();
        setStatuses(status);

    }


    // Setters

    @Override
    public String getStageName() {
        return stageName;
    }

    @Override
    public boolean equals(Entertainment entertainment) {
        Episode inEpisode = (Episode) entertainment;
        return getFranchise().equals(inEpisode.getFranchise()) && episodeNum == inEpisode.getEpisodeNum();
    }

    @Override
    public String createStageName() {
        if (getTitle() == null || getTitle().isBlank())
            return "Episode " + episodeNum;
        return getFranchise();
    }

    public String visualEpisodeNum() {
        if (episodeNum == 0)
            return "Pilot";
        return episodeNum + ".";
    }
}
