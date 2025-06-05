package com.alphag947.api;

import java.util.ArrayList;

import com.alphag947.backend.Backend;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;
import com.alphag947.backend.searchengine.SearchEngine;
import com.alphag947.controller.uiController.MainframeController;

import javafx.scene.layout.BorderPane;

public class AppApi {

    private ConsoleLogger cl;
    private MainframeController mainframeController;
    private Backend backend;
    private SearchEngine searchEngine;

    public AppApi() {
        cl = LoggerFactory.getConsoleLogger();
    }

    public void setMainframeController(MainframeController mfc) {
        this.mainframeController = mfc;
    }

    public void createBackend() {
        backend = new Backend();
        searchEngine = new SearchEngine();
    }

    public void setBackend() {
        backend.start();
        searchEngine.setData(backend.getEntertainmentList());
    }

    public Show getShow(int showId) throws Exception {
        for (Show show : backend.getShowList()) {
            if (showId == show.getSeasonID()) {
                cl.log(this, "ShowId " + show.getSeasonID() + ": " + show.getStageName());
                return show;
            }
        }
        throw new Exception("Show with Id \"" + showId + "\" does not exist");
    }

    public void setFrontend() {

        mainframeController.setEntertainments(backend.getEntertainmentList());

    }

    public BorderPane getEntertainmentModule(int base_num) {
        BorderPane module = mainframeController.getModule();
        return module;
    }

    public int getEntertainmentPrimaryStatusByIndex(int base_num, int child_num) {
        try {
            Entertainment entertainment = backend.getEntertainmentById(base_num - 1);

            if (entertainment != null && child_num == 0)
                return entertainment.getPrimaryStatus();

            if (entertainment instanceof Show && child_num > 0) {
                Show show = (Show) entertainment;
                Episode episode;
                try {
                    episode = show.getEpisode(child_num);
                    return episode.getPrimaryStatus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            throw new Exception("Entertainment Not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    private Entertainment getEntertainmentByIndex(int parent_value, int child_value) {

        if (parent_value < 0 || parent_value > backend.getEntertainmentList().size())
            return null;

        try {
            Entertainment entertainment = backend.getEntertainmentById(parent_value - 1);

            if (entertainment != null && child_value == 0)
                return entertainment;

            if (entertainment instanceof Show && child_value > 0) {
                Show show = (Show) entertainment;

                for (Episode episode : show.getEpisodes()) {
                    if (episode.getEpisodeNum() == child_value)
                        return episode;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setEntertainmentPrimaryStatus(int parent_value, int child_value, int new_primary_status) {
        Entertainment entertainment = getEntertainmentByIndex(parent_value, child_value);
        System.out.println("Before: " + entertainment.getPrimaryStatus());
        entertainment.setPrimaryStatus(new_primary_status);
        System.out.println("After: " + entertainment.getPrimaryStatus());
    }

    public ArrayList<String> getEntertainmentByPrimartStatus(int primaryStatus) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        for (Entertainment entertainment : backend.getEntertainmentList()) {
            ++i;
            if (entertainment.getPrimaryStatus() == primaryStatus)
                list.add(i + "");

            if (entertainment instanceof Show) {
                Show show = (Show) entertainment;
                for (Episode episode : show.getEpisodes())
                    if (episode.getPrimaryStatus() == primaryStatus)
                        list.add(i + "-" + episode.getEpisodeNum());
            }
        }
        return list;
    }

    public void printData() {
        for (Entertainment entertainment : backend.getEntertainmentList()) {
            if (entertainment instanceof Movie) {
                Movie movie = (Movie) entertainment;
                cl.dbg(movie.getType() + " (" + movie.getPrimaryStatus() + "): "
                        + movie.getStageName());
            } else if (entertainment instanceof Show) {
                Show show = (Show) entertainment;
                cl.dbg(show.getType() + " (" + show.getPrimaryStatus() + "): "
                        + show.getStageName());

                for (Episode episode : show.getEpisodes()) {
                    System.out.print("\t");
                    cl.dbg(episode.getType() + "(" + episode.getPrimaryStatus() + ") > " + episode.getEpisodeNum()
                            + ": " + episode.getEpisodeTitle());
                }
            }
        }
    }
}
