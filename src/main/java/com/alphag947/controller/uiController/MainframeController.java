package com.alphag947.controller.uiController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.alphag947.App;
import com.alphag947.api.AppApi;
import com.alphag947.api.AppApiFactory;
import com.alphag947.backend.entertainment.*;
import com.alphag947.backend.fxmlLoading.*;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;
import com.alphag947.controller.ParentController;
import com.alphag947.controller.entertainmentViewer.moduleViewer.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainframeController extends ParentController {

    AppApi api;

    private BorderPane noEpisodesPane = new BorderPane();
    private Label noEpisodesLabel = new Label("No Entertainment. Create Movie or Shows to view here");

    private int listId = 0;
    // private int moduleHeight = 40;

    private boolean viewerEnabled;

    /*
     * Menu Bar
     * 
     * mi_app_close: closes the app
     * mi_test: tests and event like mouse clicke keyboard click and other.
     * 
     * Sorting Modules:
     * sortByName: sorts data in ListView by stage name
     * sortById: sorts data by their ids
     * sortByTypeTheName: sorts data by their type and then by their name.
     * 
     * Window Size:
     * mi_75_percent: makes the window width and height 75% of width and height
     * mi_80_percent: makes the window width and height 80% of width and height
     * mi_fullscreen: makes app fullscreen
     * 
     * 
     * 
     */
    @FXML private MenuBar menubar;
    @FXML private MenuItem mi_app_close;
    @FXML private MenuItem mi_test;

    @FXML private MenuItem mi_sortByName;
    @FXML private MenuItem mi_sortById;
    @FXML private MenuItem mi_sortByTypeTheName;

    @FXML private MenuItem mi_viewer;

    @FXML private MenuItem mi_75_percent;
    @FXML private MenuItem mi_80_percent;
    @FXML private MenuItem mi_100_percent;
    @FXML private MenuItem mi_fullscreen;

    @FXML private TextField search_bar_textfield;
    @FXML private Button search_bar_search_button;

    @FXML private SplitPane splitPane;

    @FXML private ListView<BorderPane> list_view;
    @FXML private AnchorPane info_viewer_placeholder;

    @FXML
    public void initialize() {

        mi_75_percent.setOnAction(e -> App.setStageSize(0.75, 0.75));
        mi_80_percent.setOnAction(e -> App.setStageSize(0.8, 0.8));
        mi_100_percent.setOnAction(e -> App.setStageSize(1, 1));
        mi_fullscreen.setOnAction(e -> App.getStage().setFullScreen(true));

        api = AppApiFactory.getApi();
        viewerEnabled = false;
        setViewerWidth();
    }

    @FXML
    public void testAction() {
    }

    @FXML
    public void closeApp() {

        ConsoleLogger cl = LoggerFactory.getConsoleLogger();
        cl.log(this, "Closing App");

        System.exit(0);
        // TO-DO: get window todo save and then exit
    }

    public boolean setEntertainments(ArrayList<Entertainment> entertainments) {

        if (entertainments.size() <= 0) {
            cl.err(new Exception("Data List is <= 0"));

            noEpisodesPane.setPrefHeight(41);
            noEpisodesPane.setCenter(noEpisodesLabel);
            noEpisodesPane.getStyleClass().addAll("module", "no_episode_pane");
            noEpisodesLabel.getStyleClass().addAll("no_episode_label");
            noEpisodesLabel.setTextAlignment(TextAlignment.CENTER);

            list_view.getItems().add(noEpisodesPane);
            return false;
        }

        listId = 0;
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

    @FXML
    public void sortModulesByName() {
        cl.dbg("Sorting by Name");
        api.sortData("byName");
        list_view.getItems().clear();
        api.setFrontend();
    }

    @FXML
    public void sortModulesById() {
        cl.dbg("Sorting by Id");
        api.sortData("byId");
        list_view.getItems().clear();
        api.setFrontend();
    }

    @FXML
    public void sortModulesByTypeTheName() {
        cl.dbg("Sorting by Type");
        api.sortData("byType");
        list_view.getItems().clear();
        api.setFrontend();
    }

    @FXML
    public void setViewerWidth() {
        // cl.dbg(this, "Divider Clicked");
        if (!viewerEnabled) {
            mi_viewer.setText("Close Viewer");
            viewerEnabled = true;
            info_viewer_placeholder.setPrefWidth(300);
            splitPane.setDividerPositions(0.4);
        } else {
            mi_viewer.setText("Open Viewer");
            viewerEnabled = false;
            info_viewer_placeholder.setPrefWidth(0);
            splitPane.setDividerPositions(0.0);
        }
    }
}
