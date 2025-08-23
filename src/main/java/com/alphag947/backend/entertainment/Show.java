package com.alphag947.backend.entertainment;

import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.backend.entertainment.exception.EntertainmentStatusNotFoundException;
import com.alphag947.backend.entertainment.exception.EpisodeNotfoundException;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class Show extends Entertainment {

    private final ArrayList<Episode> episodes;
    private final String visualEpisodeCount;
    private final String visualSeason;
    private int seasonID;
    private int seasonNum;
    private String seasonName;
    private int totalEpisodes;
    private boolean isCompletedEpisodes;
    private boolean isReleasedEpisodes;
    private boolean isUpcomingEpisodes;
    private ConsoleLogger logger = LoggerFactory.getConsoleLogger();

    public Show(
            int id,
            EntertainmentType type,

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

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public int getSeasonNum() {
        return seasonNum;
    }

    public void setSeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }

    // Setters

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void addEpisode(Episode episode) {
        switch (episode.getPrimaryStatus()) {
            case COMPLETED:
                isCompletedEpisodes = true;
                break;
            case RELEASED:
                isCompletedEpisodes = true;
                break;
            case UPCOMING:
                isCompletedEpisodes = true;
                break;
            default:
                logger.err(new EntertainmentStatusNotFoundException(episode.getPrimaryStatus()));
        }

        episodes.add(episode);
    }

    public Episode getEpisode(int episodeNum) throws EpisodeNotfoundException {

        for (Episode episode : episodes)
            if (episode.getEpisodeNum() == episodeNum)
                return episode;

        throw new EpisodeNotfoundException(episodeNum);
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

    public boolean hasTitle() {
        return !(seasonName == null || seasonName.isEmpty() || seasonName.isBlank());
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
