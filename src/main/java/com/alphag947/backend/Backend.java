package com.alphag947.backend;

import com.alphag947.App;
import com.alphag947.api.Api;
import com.alphag947.api.ApiFactory;
import com.alphag947.api.SortType;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.entertainment.enumeration.EntertainmentType;
import com.alphag947.backend.entertainment.exception.EntertainmentIdNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentIdOutOfBoundsException;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentStatusNotFoundException;
import com.alphag947.backend.searchengine.SearchEngine;
import com.alphag947.backend.settings.SettingKey;
import com.alphag947.backend.settings.Settings;
import com.alphag947.backend.settings.SettingsAccessDeniedException;
import com.alphag947.backend.settings.SettingsFactory;
import com.alphag947.v2.file.DataBaseHandler;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Backend {

    /**
     * Logger for Backend
     */
    private final Logger LOGGER = Logger.getLogger(Backend.class);

    /**
     * Contains all the data for movies, tvshows, etc
     */
    private final ArrayList<Entertainment> entertainmentList;

    /**
     * contains all the episodes when reading the data.
     */
    private final ArrayList<Episode> episodeList;

    /**
     * contains all the shows.
     */
    private final ArrayList<Show> showList;
    /**
     * api for the backend
     * API is universal and only 1 exists so can call ApiFactory here.
     *
     * @see Api
     * @see ApiFactory
     */
    private final Api api = ApiFactory.getApi();
    /**
     * Contains the supposed to be only settings file so it is not universal
     * the SettingsFactory block request if there are many Settings objs
     */
    private Settings settings;
    /**
     * this is engine used for searching information. any information that needs to be found happens here
     */
    private SearchEngine searchEngine;
    /**
     * This is the handler for databases.
     */
    private DataBaseHandler dbh;


    public Backend() {
        entertainmentList = new ArrayList<>();
        episodeList = new ArrayList<>();
        showList = new ArrayList<>();
        try {
            settings = SettingsFactory.getSettings();
            dbh = new DataBaseHandler();
        } catch (SettingsAccessDeniedException e) {
            LOGGER.error(e);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        searchEngine = new SearchEngine();
    }

    public void start() {


        LOGGER.info(System.getProperty("java.version"));

        /*
         * gets the name of the user, it is the login name of the user.
         */
        String user = System.getProperty("user.name");
        LOGGER.info(user);

        /*
         * uses the user to generate the database folder if it does not exist
         */
        File folder = new File("C:/Users/" + user + "/AppData/Local/AlphaGeN_Studios/EntertainmentTracker/database");
        if (!(folder.exists() && folder.isDirectory()))
            folder.mkdirs();
        else LOGGER.info("Directory Exists");

        /*
         * uses the user to generate default settings file if it does not exist
         */
        File settingsFile = new File("C:/Users/" + user + "/AppData/Local/AlphaGeN_Studios/EntertainmentTracker/settings.txt");
        if (!settingsFile.exists()) {
            // TODO: generate default settings folder
            LOGGER.info("Create Settings File");
        }

        // FIXME: Enable code below
        // dbh.setPath(folder.getAbsolutePath());

        setEntertainments(readData());
        String sst = settings.getValue(SettingKey.SORT_TYPE);
        sortData(SortType.valueOf(sst));
        searchEngine.setData(entertainmentList);
    }

    public void sortData(SortType st) {
        switch (st) {
            case BY_NAME:
                entertainmentList
                        .sort(Comparator.comparing(
                                Entertainment::getStageName));
                break;
            case BY_ID:
                entertainmentList
                        .sort(Comparator.comparing(
                                Entertainment::getId));
                break;
            case BY_TYPE:
                entertainmentList
                        .sort(Comparator.comparing(
                                Entertainment::getType));
                break;

            case BY_DATE:
                entertainmentList.sort(Comparator.comparing(Entertainment::getDate));
                break;

            default:
                LOGGER.error(new Exception("Sort Type \"" + st + "\"does not exist"));
        }
    }

    public File getDataFile() {
        URL url = getClass().getResource("/com/alphag947/data/data/data.txt");
        if (url == null) LOGGER.info("url is null");
        File file = new File(url.getFile());
        return file;
    }

    public ArrayList<String[]> readData() {

        ArrayList<String[]> parsedData = new ArrayList<>();
        boolean usingOGFile = false;

        if (usingOGFile) {
            try (BufferedReader fileReader = new BufferedReader(
                    new FileReader(new File(settings.getValue(SettingKey.DATA_FILEPATH))))) {

                String line;

                while ((line = fileReader.readLine()) != null) {

                    if (!(line.startsWith("//"))) {
                        parsedData.add(line.split("<##>")); // level 1 parsed data
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        } else {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(getDataFile()))) {

                String line;

                while ((line = fileReader.readLine()) != null) {

                    if (!(line.startsWith("//"))) {
                        parsedData.add(line.split("<##>")); // level 1 parsed data
                    }
                }

            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        if (App.DEBUG)
            LOGGER.info("Parse data length: " + parsedData.size());
        return parsedData;
    }

    public Entertainment createEntertainment(String[] list) {

        // cl.dbg(this, list[0]);
        switch (list[1]) {
            case "Movie":
                return new Movie(
                        Integer.parseInt(list[0]), // Id
                        EntertainmentType.MOVIE, // type
                        list[2], // franchise
                        list[3], // title
                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags
                        Integer.parseInt(list[6]), // duration
                        LocalDate.parse(list[7]), // date
                        list[8].split("<<>>")); // production companies

            case "TV Show":
                return new Show(
                        Integer.parseInt(list[0]), // Id
                        EntertainmentType.TVSHOW, // type: TV Show or Anime
                        list[2], // franchise
                        list[3], // title

                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags

                        Integer.parseInt(list[6]), // season number
                        Integer.parseInt(list[7]), // total episodes

                        LocalDate.parse(list[8]), // date
                        Integer.parseInt(list[9]) // season ID
                );
            case "Anime":
                return new Show(
                        Integer.parseInt(list[0]), // Id
                        EntertainmentType.ANIME, // type: TV Show or Anime
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
                        EntertainmentType.EPISODE, // type

                        Integer.parseInt(list[2]), // episode number

                        list[3], // episode title

                        list[4].split("<<>>"), // status
                        list[5].split("<<>>"), // tags

                        Integer.parseInt(list[6]), // duration

                        LocalDate.parse(list[7]), // date
                        Integer.parseInt(list[8]) // season ID
                );

            default:
                LOGGER.error((new EntertainmentStatusNotFoundException(EntertainmentStatus.resolve(list[1]))));
                return new Entertainment(
                        0, // Id
                        EntertainmentType.NULL, // type
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

            boolean successful = false;
            try {
                successful = addEpisodeToShow(showList, episode);
            } catch (Exception e) {
                LOGGER.error(e);
            }
            if (successful)
                removedEpisodes.remove(episode);
        }
        episodeList.removeAll(removedEpisodes);
    }

    private boolean addEpisodeToShow(ArrayList<Show> allShows, Episode episode) throws Exception {

        // checks if episode id is out of range
        try {
            if (episode.getSeasonID() < 0 || episode.getSeasonID() > allShows.size())
                LOGGER.error(new Exception("This episode's ShowID is out of range: "));

        } catch (Exception e) {
            LOGGER.error(e);
        }

        // checks if show exists
        boolean showExists = false;
        boolean episodeAdded = false;
        for (Show show : allShows) {
            if (show.getSeasonID() == episode.getSeasonID()) {
                showExists = true;
                episodeAdded = true;
                show.addEpisode(episode);
            }
        }

        // throws error if show does not exist
        if (!showExists)
            throw new Exception("Show with ShowId \"" + episode.getSeasonID() + "\" does not exist");

        /*
         * show exists but episodes that don't have shows not added need to stay on the
         * list
         */
        if (!episodeAdded)
            throw new Exception("Episode \"" + episode.getTitle() + "\" does not have show");

        return episodeAdded;
    }

    public ArrayList<Entertainment> getEntertainmentList() {
        return entertainmentList;
    }

    public Entertainment getEntertainmentById(int id)
            throws EntertainmentIdOutOfBoundsException, EntertainmentIdNotFoundException {

        for (Entertainment entertainment : entertainmentList) {
            if (entertainment.getId() == id) {
                if (App.DEBUG)
                    LOGGER.info("id: " + id);
                return entertainment;
            }
        }

        throw new EntertainmentIdNotFoundException(id);
    }

    public ArrayList<Show> getShowList() {
        return showList;
    }

    public void writeData() throws Exception {
        int i = 0;
        for (Entertainment entertainment : entertainmentList) {
            i++;
            switch (entertainment.getType()) {
                case MOVIE:
                    Movie movie = (Movie) entertainment;
                    //                        String mdl = movie.getDataLine();
                    System.out.println(movie.toString());
                    break;
                case ANIME:
                    break;
                case TVSHOW:
                    break;
                case EPISODE:
                    break;
                case NULL:
                    throw new Exception("entertainment type is \"NULL\" > " + entertainment.getStageName());
                default:
                    throw new Exception("entertainment type > " + entertainment.getStageName() + " does not exist");
            }
        }
    }

    public Show getShow(int seasonID) throws EntertainmentNotFoundException {
        for (Show show : showList) {
            if (show.getSeasonID() == seasonID) return show;
        }
        throw new EntertainmentNotFoundException("Show with id \"" + seasonID + "\" not found");
    }
}