package com.alphag947.backend.entertainment.exception;

public class EpisodeNotfoundException extends EntertainmentNotFoundException {
    public EpisodeNotfoundException(int episodeNum) {
        super("Episode Number \"" + episodeNum + "\" does not exist");
    }
}
