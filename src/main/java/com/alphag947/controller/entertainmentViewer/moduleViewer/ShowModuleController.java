package com.alphag947.controller.entertainmentViewer.moduleViewer;

import java.util.ArrayList;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Show;
import com.alphag947.backend.entertainment.exception.EntertainmentException;
import com.alphag947.controller.ModuleController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class ShowModuleController extends ModuleController {

    @FXML private Label module_id;
    @FXML private Label module_name;
    @FXML private Label module_season;
    @FXML private Label module_episodes;
    @FXML private ListView<BorderPane> module_list;

    @FXML private BorderPane module;
    @FXML private Label status_indicator;

    @FXML private BorderPane module_info;

    ArrayList<BorderPane> episodeList;

    private boolean shrunk;

    double multiplier = 1;
    double moduleHeight = 41 * multiplier;

    private String upArrow = "▲";
    private String downArrow = "▼";

    private BorderPane noEpisodesPane = new BorderPane();
    private Label noEpisodesLabel = new Label("No Episodes. Add episode to view here! (Click to create Episode)");

    @Override
    public void setEntertainment(Entertainment entertainment) {
        shrunk = true;
        super.setEntertainment(entertainment);
        setData();
    }

    @FXML
    public void viewData() {
        cl.dbg("Viewing: " + ((Show) getEntertainment()).getStageName() + ", "
                + ((Show) getEntertainment()).getSeasonNum());
    }

    public void addEpisodeModule(BorderPane sm) {
        if (module_list.getItems().contains(noEpisodesPane))
            module_list.getItems().remove(noEpisodesPane);
        module_list.getItems().add(sm);
    }

    public void removeEpisode(BorderPane sm) {
        module_list.getItems().remove(sm);
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
        } catch (EntertainmentException e) {
            e.printStackTrace();
        }

        if (show.getEpisodes().size() <= 0)
            shrunk = true;
        shrink_grow_module();

        if (show.getEpisodes().size() == 0) {
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
            status_indicator.setText(upArrow);
            shrunk = true;
            module.requestFocus();
        }
    }

    public void addEpisodeModules(ArrayList<BorderPane> episodes) {
        if (module_list.getItems().contains(noEpisodesPane))
            module_list.getItems().remove(noEpisodesPane);
        module_list.getItems().addAll(episodes);
        module.requestLayout();

    }

    public ListView<BorderPane> getListView() {
        return module_list;
    }

    public Label getStatusIndicator() {
        return status_indicator;
    }

    public BorderPane getModule() {
        return module;
    }
}
