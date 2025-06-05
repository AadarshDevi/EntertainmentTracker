package com.alphag947.controller.uiController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.alphag947.backend.entertainment.*;
import com.alphag947.backend.fxmlLoading.*;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;
import com.alphag947.controller.ParentController;
import com.alphag947.controller.entertainmentViewer.moduleViewer.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainframeController extends ParentController {

    @FXML private MenuBar menubar;
    @FXML private MenuItem mi_app_close;
    @FXML private MenuItem mi_test;

    @FXML private TextField search_bar_textfield;
    @FXML private Button search_bar_search_button;

    @FXML private ListView<BorderPane> list_view;
    @FXML private AnchorPane info_viewer_placeholder;

    private int listId = 0;

    @FXML
    public void initialize() {
    }

    @FXML
    public void testAction() {
    }

    @FXML
    public void closeApp() {

        ConsoleLogger cl = LoggerFactory.getConsoleLogger();
        cl.log(this, "Closing App");

        System.exit(0);
        // TODO: get window top do save and then exit
    }

    public boolean setEntertainments(ArrayList<Entertainment> entertainments) {

        if (entertainments.size() <= 0) {
            cl.err(new Exception("Data List is <= 0"));
            return false;
        }

        for (Entertainment entertainment : entertainments) {
            if (entertainment instanceof Movie) {
                list_view.getItems().add(createModule((Movie) entertainment));
            } else if (entertainment instanceof Show) {
                list_view.getItems().add(createModule((Show) entertainment));
            } else {
                cl.err(new Exception("\"entertainment\" is an unknown instance"));
            }
        }
        return true;
    }

    public BorderPane createModule(Movie movie) {
        FXMLPackage<BorderPane, MovieModuleController> mmp = FXMLFactory.getFxmlManager().getMovieModule();
        BorderPane mm = mmp.getPane();
        MovieModuleController mmc = mmp.getController();
        mmc.setId(++listId);
        mmc.setEntertainment(movie);
        return mm;
    }

    public BorderPane createModule(Show show) {

        FXMLPackage<BorderPane, ShowModuleController> smp = FXMLFactory.getFxmlManager().getShowModule();
        BorderPane sm = smp.getPane();
        ShowModuleController smc = smp.getController();
        smc.setId(++listId);
        smc.setEntertainment(show);

        smc.getModule().setTop(null);
        smc.getModule().setBottom(null);

        ArrayList<Episode> episodes = show.getEpisodes();
        Collections.sort(episodes, Comparator.comparing(Episode::getEpisodeNum));

        for (Episode episode : episodes) {
            smc.addEpisodeModule(createModule(episode));
        }

        return sm;
    }

    public BorderPane createModule(Episode episode) {
        FXMLPackage<BorderPane, EpisodeModuleController> emp = FXMLFactory.getFxmlManager().getEpisodeModule();
        BorderPane em = emp.getPane();
        EpisodeModuleController emc = emp.getController();
        emc.setEntertainment(episode);
        emc.setId(episode.getEpisodeNum());
        return em;
    }

    public BorderPane getModule() {
        return null;
    }

    @FXML
    public void minimizeApp() {
        Stage stage = (Stage) menubar.getScene().getWindow();
        stage.setIconified(true);
    }

}
