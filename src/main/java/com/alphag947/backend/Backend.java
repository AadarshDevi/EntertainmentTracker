package com.alphag947.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import com.alphag947.backend.Settings.Settings;
import com.alphag947.backend.Settings.SettingsFactory;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;

public class Backend {

    private ConsoleLogger cl;
    // private AppApi api;

    private ArrayList<Entertainment> entertainmentList;
    private ArrayList<Episode> episodeList;
    private ArrayList<Show> showList;
    private Settings settings;

    public Backend() {
        settings = SettingsFactory.getSettings();
        cl = LoggerFactory.getConsoleLogger();
        // api = AppApiFactory.getApi();

        entertainmentList = new ArrayList<>();
        episodeList = new ArrayList<>();
        showList = new ArrayList<>();
    }

    public void start() {
        setEntertainments(readData());
        entertainmentList
                .sort(Comparator.comparing(Entertainment::getFranchise).thenComparing(Entertainment::getTitle));
    }

    public ArrayList<String[]> readData() {

        ArrayList<String[]> parsedData = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(
                new FileReader(new File(settings.getDataPath())))) {

            String line;

            while ((line = fileReader.readLine()) != null) {

                if (!(line.startsWith("//"))) {
                    parsedData.add(line.split("<##>")); // level 1 parsed data
                }
            }
        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cl.hlt("Parse data length: " + parsedData.size());

        return parsedData;
    }

    public Entertainment createEntertainment(String[] list) {

        switch (list[1]) {
            case "Movie":
                return new Movie(
                        Integer.parseInt(list[0]), // Id
                        list[1], // type
                        list[2], // franchise
                        list[3], // title
                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags
                        Integer.parseInt(list[6]), // duration
                        LocalDate.parse(list[7]), // date
                        list[8].split("<<>>")); // production companies

            case "TV Show":
            case "Anime":
                return new Show(
                        Integer.parseInt(list[0]), // Id
                        list[1], // type: TV Show or Anime
                        list[2], // franchise
                        list[3], // title

                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags

                        Integer.parseInt(list[6]), // season number
                        Integer.parseInt(list[7]), // total episodes

                        LocalDate.parse(list[8]), // date
                        Integer.parseInt(list[9]) // season ID
                );

            case "Episode":
                return new Episode(
                        Integer.parseInt(list[0]), // Id
                        list[1], // type

                        Integer.parseInt(list[2]), // episode number

                        list[3], // episode title

                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags

                        Integer.parseInt(list[6]), // duration

                        LocalDate.parse(list[7]), // date
                        Integer.parseInt(list[8]) // season ID
                );

            //

            default:
                cl.err(new Exception("Entertainment Type \"" + list[1] + "\" does not exist"));
                return new Entertainment(
                        0, // Id
                        list[1], // type
                        list[2], // franchise
                        list[3], // title
                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags
                        LocalDate.parse(list[8]) // date
                );
        }
    }

    public void setEntertainments(ArrayList<String[]> parsedData) {

        for (String[] parsed : parsedData) {

            Entertainment entertainment = createEntertainment(parsed);

            if (entertainment instanceof Episode) {
                episodeList.add((Episode) entertainment);
            } else if (entertainment instanceof Show) {
                showList.add((Show) entertainment);
                entertainmentList.add(entertainment);
            } else {
                entertainmentList.add(entertainment);
            }
        }

        ArrayList<Episode> removedEpisodes = new ArrayList<>();
        for (Episode episode : episodeList) {

            boolean successful = addEpisodeToShow(showList, episode);
            if (successful)
                removedEpisodes.remove(episode);
        }
        episodeList.removeAll(removedEpisodes);
    }

    private boolean addEpisodeToShow(ArrayList<Show> allShows, Episode episode) {

        // checks if episode id is out of range
        try {
            if (episode.getSeasonID() < 0 || episode.getSeasonID() > allShows.size())
                cl.ex(this, new Exception("This episode's ShowID is out of range: "));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // checks if show exists
        boolean showExists = false;
        boolean episodeAdded = false;
        for (Show show : allShows) {
            if (show.getSeasonID() == episode.getSeasonID()) {
                showExists = true;
                episodeAdded = true;
                show.addEpisode(episode);
                // cl.log("Episode added to Show: " +
                // show.getStageName() + ", Season " + show.getSeasonNum() +
                // " Episode " + episode.getEpisodeNum() + ": " + episode.getEpisodeTitle());
            }
        }

        // throws error if show does not exist
        try {
            if (!showExists)
                cl.ex(this, new Exception("Show with ShowId \"" + episode.getSeasonID() + "\" does not exist"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * show exists but episodes that dont have shows not added need to stay on the
         * list
         */
        try {
            if (!episodeAdded) {
                cl.ex(this, new Exception("Episode \"" + episode.getEpisodeTitle() + "\" does not have show"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return episodeAdded;
    }

    public ArrayList<Entertainment> getEntertainmentList() {
        return entertainmentList;
    }

    public Entertainment getEntertainmentById(int id) throws Exception {
        if (id < 0 || id > entertainmentList.size())
            throw new Exception("Id out of bounds");

        for (Entertainment entertainment : entertainmentList) {
            if (entertainment.getId() == id)
                return entertainment;
        }

        throw new Exception("Entertainment with id: " + id + " does not exist");
    }

    public ArrayList<Show> getShowList() {
        return showList;
    }
}
