package com.alphag947.backend.entertainment.episodeer;

import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Show;

public class EpisodePackage {
    private final Show show;
    private final Episode episode;

    public EpisodePackage(Show show, Episode episode) {
        this.episode = episode;
        this.show = show;
    }

    public Episode getEpisode() {
        return episode;
    }

    public Show getShow() {
        return show;
    }
}
