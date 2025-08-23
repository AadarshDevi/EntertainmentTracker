package com.alphag947.api;

import com.alphag947.App;
import com.alphag947.SceneFactory.SceneManager;
import com.alphag947.backend.Backend;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.entertainment.enumeration.EntertainmentStatus;
import com.alphag947.backend.entertainment.exception.EntertainmentIdNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentIdOutOfBoundsException;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.backend.fxmlLoading.FXMLFactory;
import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.backend.fxmlLoading.exception.FXMLNullException;
import com.alphag947.backend.searchengine.SearchEngine;
import com.alphag947.controller.TestController;
import com.alphag947.controller.uiController.MainframeController;
import com.alphag947.v2.file.DataBaseHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Api {

    private static final Logger LOGGER = LogManager.getLogger(Api.class);
    private final SceneManager sceneManager;
    private MainframeController mainframeController;
    private Backend backend;
    private SearchEngine searchEngine;
    private DataBaseHandler dbh;

    public Api() {
        sceneManager = new SceneManager();
        LOGGER.info("SceneManager ready. Set stage");
    }

    public MainframeController getMainframeController() {
        return this.mainframeController;
    }

    public void setMainframeController(MainframeController mfc) {
        this.mainframeController = mfc;
    }

    public void createBackend() {
        backend = new Backend();
        dbh = new DataBaseHandler();
        searchEngine = new SearchEngine();
    }

    public void setBackend(String dataBasePath) {
        dbh.setPath(dataBasePath);
        backend.start();
        searchEngine.setData(backend.getEntertainmentList());
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public Show getShow(int showId) throws EntertainmentIdNotFoundException {
        for (Show show : backend.getShowList()) {
            if (showId == show.getSeasonID()) {
                if (App.DEBUG)
                    LOGGER.info(String.format("ShowId %s: %s", show.getSeasonID(), show.getStageName()));
                return show;
            }
        }
        throw new EntertainmentIdNotFoundException(showId);
    }

    public void setFrontend() {

        mainframeController.setEntertainments(backend.getEntertainmentList());

    }

    public BorderPane getEntertainmentModule(int base_num) {
        return mainframeController.getModule();
    }

    public EntertainmentStatus getEntertainmentPrimaryStatusByIndex(int base_num, int child_num) {
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
                    LOGGER.error(e);
                }
            }

            // FIXME: correct error handling
            throw new EntertainmentNotFoundException();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return EntertainmentStatus.NULL;

    }

    private Entertainment getEntertainmentByIndex(int parent_value, int child_value) throws EntertainmentIdNotFoundException, EntertainmentIdOutOfBoundsException {

        if (parent_value < 0 || parent_value > backend.getEntertainmentList().size())
            return null;


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
        return null;
    }

    public void setEntertainmentPrimaryStatus(int parent_value, int child_value,
                                              EntertainmentStatus new_primary_status) {
        Entertainment entertainment = null;
        try {
            entertainment = getEntertainmentByIndex(parent_value, child_value);
//            System.out.println("Before: " + entertainment.getPrimaryStatus());
            entertainment.setPrimaryStatus(new_primary_status);
            System.out.println("After: " + entertainment.getPrimaryStatus());
        } catch (EntertainmentIdNotFoundException | EntertainmentIdOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getEntertainmentByPrimartStatus(EntertainmentStatus primaryStatus) {
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
                if (App.DEBUG)
                    LOGGER.info(movie.getType() + " (" + movie.getPrimaryStatus() + "): "
                            + movie.getStageName());
            } else if (entertainment instanceof Show) {
                Show show = (Show) entertainment;
                if (App.DEBUG)
                    LOGGER.info(show.getType() + " (" + show.getPrimaryStatus() + "): "
                            + show.getStageName());

                for (Episode episode : show.getEpisodes()) {
                    System.out.print("\t");
                    if (App.DEBUG)
                        LOGGER.info(episode.getType() + "(" + episode.getPrimaryStatus() + ") > " + episode.getEpisodeNum()
                                + ": " + episode.getEpisodeTitle());
                }
            }
        }
    }

    public void sortData(SortType st) {
        backend.sortData(st);
    }

    public SortType convert(String string) {
        switch (string) {
            case "byName":
                return SortType.BY_NAME;
            case "byId":
                return SortType.BY_ID;
            case "byDate":
                return SortType.BY_DATE;
            case "byType":
                return SortType.BY_TYPE;
            default:
                return SortType.BY_NULL;
        }
    }

    public void viewEntertainment(int id) {
        try {
            Entertainment entertainment = backend.getEntertainmentById(id);
            mainframeController.viewEntertainment(entertainment);
            if (App.DEBUG) LOGGER.info(id + ": " + entertainment.getStageName());
        } catch (EntertainmentNotFoundException e) {
            LOGGER.error(e);
        }
    }

    public void viewEntertainment(Episode episode) {
        try {
            Show show = backend.getShow(episode.getSeasonID());
            Episode internalEpisode = show.getEpisode(episode.getEpisodeNum());
            mainframeController.viewEntertainment(internalEpisode);
        } catch (EntertainmentNotFoundException e) {
            LOGGER.error(e);
        }
    }

    public Entertainment getEntertainmentById(int entertainmentId) throws EntertainmentIdNotFoundException {
        try {
            return backend.getEntertainmentById(entertainmentId);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        throw new EntertainmentIdNotFoundException(entertainmentId);
    }

    public void closeApp() {
        try {
            backend.writeData();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void test() {
        try {
            FXMLPackage<BorderPane, TestController> tp = FXMLFactory.getFxmlManager().getTestFrame();

            BorderPane tbp = tp.getPane();
            TestController tc = tp.getController();

            LOGGER.info("TestObject: " + tbp);
            sceneManager.setScene(new Scene(tbp));

        } catch (FXMLNullException e) {
            throw new RuntimeException(e);
        }

    }

    public void readFilePath(String filepath) {
        // TODO: write settings reading here
        LOGGER.info("Put settings file read through");
    }
}
