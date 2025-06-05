package com.alphag947.backend.entertainment;

import java.time.LocalDate;
import java.util.ArrayList;

import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;

public class Show extends Entertainment {

    private int seasonID;
    private int seasonNum;
    private String seasonName;
    private int totalEpisodes;

    private ArrayList<Episode> episodes;

    private boolean isCompletedEpisodes;
    private boolean isReleasedEpisodes;
    private boolean isUpcomingEpisodes;

    private String visualEpisodeCount;
    private String visualSeason;

    private ConsoleLogger logger = LoggerFactory.getConsoleLogger();

    public Show(
            int id,
            String type,

            String franchise,
            String title,

            String[] statuses,
            String[] tags,

            int seasonNum,
            int totalEpisodes,
            LocalDate date,
            int seasonID

    ) {
        super(id, type, franchise, title, statuses, tags, date);
        this.seasonNum = seasonNum;
        this.totalEpisodes = totalEpisodes;
        this.seasonID = seasonID;
        episodes = new ArrayList<>();

        this.seasonName = (seasonName == null || seasonName.isEmpty()) ? null : seasonName;

        isCompletedEpisodes = false;
        isReleasedEpisodes = false;
        isUpcomingEpisodes = false;

        stageName = createStageName();
        visualEpisodeCount = setVisualEpisodeCount();
        visualSeason = setVisualSeason();
    }

    // Getters

    public int getSeasonID() {
        return seasonID;
    }

    public int getSeasonNum() {
        return seasonNum;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    // Setters

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public void addEpisode(Episode episode) {
        if (episode.getPrimaryStatus() == 1) {
            isCompletedEpisodes = true;
        } else if (episode.getPrimaryStatus() == 2) {
            isReleasedEpisodes = true;
        } else if (episode.getPrimaryStatus() == 3) {
            isUpcomingEpisodes = true;
        }

        // TODO: check if the episode exists in list

        episodes.add(episode);
    }

    public Episode getEpisode(int episodeNum) throws Exception {
        // if (episodeNum <= 0 && episodeNum > episodes.size())
        // return null;

        for (Episode episode : episodes)
            if (episode.getEpisodeNum() == episodeNum)
                return episode;

        logger.err(new Exception("Episode Number \"" + episodeNum + "\" does not exist"));
        return null;
    }

    private String setVisualEpisodeCount() {
        if (totalEpisodes <= 0)
            return "Unknown";
        return "Episodes: " + totalEpisodes;
    }

    private String setVisualSeason() {
        if ((seasonNum <= 0) && isPilot())
            return "Pilot";
        return "Season: " + seasonNum;
    }

    public String getVisualSeason() {
        return visualSeason;
    }

    public String getVisualEpisodeCount() {
        return visualEpisodeCount;
    }

    public boolean hasCompletedEpisodes() {
        return isCompletedEpisodes;
    }

    public boolean hasReleasedEpisodes() {
        return isReleasedEpisodes;
    }

    public boolean hasUpcomingEpisodes() {
        return isUpcomingEpisodes;
    }

}
