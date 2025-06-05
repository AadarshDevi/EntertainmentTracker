package com.alphag947.backend.entertainment;

import java.time.LocalDate;

public class Episode extends Entertainment {

    private int seasonID;
    private int episodeNum;
    private String episodeTitle;
    private int duration;

    public Episode(
            int id,
            String type,

            int episodeNum,

            String episodeTitle,

            String[] status,
            String[] tags,

            int duration,
            LocalDate date,
            int seasonID

    ) {
        super(id, type, status, tags, date);
        this.seasonID = seasonID;
        this.episodeNum = episodeNum;
        this.episodeTitle = episodeTitle;
        this.duration = duration;

        this.stageName = createStageName();
        setStatuses(status);

    }

    // Getters
    public int getSeasonID() {
        return seasonID;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String getStageName() {
        return stageName;
    }

    // Setters

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Entertainment entertainment) {
        Episode inEpisode = (Episode) entertainment;
        return episodeTitle.equals(inEpisode.getEpisodeTitle()) && episodeNum == inEpisode.getEpisodeNum();
    }

    @Override
    public String createStageName() {
        if (episodeTitle == null || episodeTitle.isBlank() || episodeTitle.isEmpty())

            return "Episode " + episodeNum;
        return episodeTitle;
    }

    public String visualEpisodeNum() {
        if (episodeNum == 0)
            return "Pilot";
        return episodeNum + ".";
    }
}
