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
import com.alphag947.controller.uiController.MainframeController;
import com.alphag947.v2.controller.main.MainController;
import com.alphag947.v2.controller.utli.FXMLReader;
import javafx.scene.layout.BorderPane;
import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

@Data
public class Api {

    private static final Logger LOGGER = LogManager.getLogger(Api.class);
    private final SceneManager sceneManager;
    private MainframeController mainframeController;
    private Backend backend;

    public Api() {
        sceneManager = new SceneManager();
        LOGGER.info("SceneManager ready. Set stage");
    }


    public void createBackend() {
        backend = new Backend();
    }

    public void setBackend() {
        backend.start();
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

            if (entertainment instanceof Show show && child_num > 0) {
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

        if (entertainment instanceof Show show && child_value > 0) {

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
            if (entertainment != null) {
                entertainment.setPrimaryStatus(new_primary_status);
                System.out.println("After: " + entertainment.getPrimaryStatus());
            }
        } catch (EntertainmentIdNotFoundException | EntertainmentIdOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getEntertainmentByPrimaryStatus(EntertainmentStatus primaryStatus) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        for (Entertainment entertainment : backend.getEntertainmentList()) {
            ++i;
            if (entertainment.getPrimaryStatus() == primaryStatus)
                list.add(i + "");

            if (entertainment instanceof Show show) {
                for (Episode episode : show.getEpisodes())
                    if (episode.getPrimaryStatus() == primaryStatus)
                        list.add(i + "-" + episode.getEpisodeNum());
            }
        }
        return list;
    }

    public void printData() {
        for (Entertainment entertainment : backend.getEntertainmentList()) {
            if (entertainment instanceof Movie movie) {
                if (App.DEBUG)
                    LOGGER.info(movie.getType() + " (" + movie.getPrimaryStatus() + "): "
                            + movie.getStageName());
            } else if (entertainment instanceof Show show) {
                if (App.DEBUG)
                    LOGGER.info(show.getType() + " (" + show.getPrimaryStatus() + "): "
                            + show.getStageName());

                for (Episode episode : show.getEpisodes()) {
                    System.out.print("\t");
                    if (App.DEBUG)
                        LOGGER.info(episode.getType() + "(" + episode.getPrimaryStatus() + ") > " + episode.getEpisodeNum()
                                + ": " + episode.getTitle());
                }
            }
        }
    }

    public void sortData(SortType st) {
        backend.sortData(st);
    }

    public SortType convert(String string) {
        return switch (string) {
            case "byName" -> SortType.BY_NAME;
            case "byId" -> SortType.BY_ID;
            case "byDate" -> SortType.BY_DATE;
            case "byType" -> SortType.BY_TYPE;
            default -> SortType.BY_NULL;
        };
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
            System.exit(0);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public void test() {
        com.alphag947.v2.controller.utli.FXMLPackage fxmlPackage = FXMLReader.getInstance().getFXML(FXMLReader.V2_HOME);
        BorderPane bp = (BorderPane) fxmlPackage.getPane();
        MainController mc = (MainController) fxmlPackage.getController();
    }

    public void readFilePath(String filepath) {
        // TODO: write settings reading here
        LOGGER.info("Put settings file read through");
    }

    public void setCliView() {
        mainframeController.setCliView();
    }

    public void resetMainUI() {
        mainframeController.resetMainUI();
    }

    public void showInCliUI(Entertainment entertainment) {
        mainframeController.showEntertainmentInCli(entertainment);
    }
}
