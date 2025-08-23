package com.alphag947.controller.entertainmentViewer.dataModule;

import com.alphag947.App;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.backend.fxmlLoading.FXMLFactory;
import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.controller.ModuleController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ShowModuleController extends ModuleController {

    private final Label noEpisodesLabel = new Label("No Episodes. Add episode to view here! (Click to create Episode)");
    private final BorderPane noEpisodesPane = new BorderPane();
    private final Logger LOGGER = Logger.getLogger(ShowModuleController.class);
    ArrayList<BorderPane> episodeList;
    double multiplier = 1;
    double moduleHeight = 41 * multiplier;
    @FXML
    private Label module_id;
    @FXML
    private Label module_name;
    @FXML
    private Label module_season;
    @FXML
    private Label module_episodes;
    @FXML
    private ListView<BorderPane> module_list;
    @FXML
    private BorderPane module;
    @FXML
    private Label status_indicator;
    @FXML
    private BorderPane module_info;
    private boolean shrunk;

    @Override
    public void setEntertainment(Entertainment entertainment) {
        shrunk = true;
        super.setEntertainment(entertainment);
        setData();
    }

    @FXML
    public void viewData() {
        if (App.DEBUG) {
            System.out.println(
                    "----------------------------------------" +
                            "----------------------------------------" +
                            "----------------------------------------"
            );

            LOGGER.info(
                    "Viewing: " +
                            getEntertainment().getStageName() +
                            " S" +
                            ((Show) getEntertainment()).getSeasonNum()
            );
        }
        api.viewEntertainment(getEntertainment().getId());
    }

    public void addEpisodeModule(Episode episode) {

        module_list.getItems().remove(noEpisodesPane);

        FXMLPackage<BorderPane, EpisodeModuleController> emp = FXMLFactory.getFxmlManager().getEpisodeModule();
        BorderPane em = emp.getPane();
        EpisodeModuleController emc = emp.getController();
        emc.setEntertainment(episode);
        emc.setId(episode.getEpisodeNum());

        module_list.getItems().add(em);
    }

    @FXML
    private void setData() {
        Show show = (Show) getEntertainment();
        module_id.setText(getId() + ".");
        module_name.setText(show.getStageName());
        module_season.setText(show.getVisualSeason());
        module_episodes.setText(show.getVisualEpisodeCount());

        try {
            setStatus(module, status_indicator, show);
        } catch (EntertainmentNotFoundException e) {
            LOGGER.error(e);
        }

        if (show.getEpisodes().size() <= 0)
            shrunk = true;
        shrink_grow_module();

        if (show.getEpisodes().isEmpty()) {
            noEpisodesPane.setPrefHeight(moduleHeight);
            noEpisodesPane.setCenter(noEpisodesLabel);
            noEpisodesPane.getStyleClass().addAll("module", "no_episode_pane");
            noEpisodesLabel.getStyleClass().addAll("no_episode_label");
            noEpisodesLabel.setTextAlignment(TextAlignment.CENTER);
            module_list.getItems().add(noEpisodesPane);
        }
    }

    @FXML
    private void shrink_grow_module() {

        if (shrunk) {
            module.setTop(null);
            module.setBottom(null);
            module_list.setPrefHeight(0);
            module.setPrefHeight(moduleHeight);
            module_list.setVisible(false);
            module_list.setManaged(false);
            status_indicator.setPrefHeight(moduleHeight);
            module.requestFocus();
            shrunk = false;
            String downArrow = "▼";
            status_indicator.setText(downArrow);
        } else {

            double listViewHeight = 5 * moduleHeight;
            if (((Show) getEntertainment()).getEpisodes().size() > 10)
                listViewHeight = 12 * moduleHeight;

            if (((Show) getEntertainment()).getEpisodes().size() <= 0)
                listViewHeight = 1 * moduleHeight * 1.4;

            module_list.setPrefHeight(listViewHeight);
            module_list.setVisible(true);
            module_list.setManaged(true);
            module.setPrefHeight(moduleHeight + listViewHeight);
            status_indicator.setPrefHeight(moduleHeight + listViewHeight);
            String upArrow = "▲";
            status_indicator.setText(upArrow);
            shrunk = true;
            module.requestFocus();
        }
    }

    public BorderPane getModule() {
        return module;
    }
}
