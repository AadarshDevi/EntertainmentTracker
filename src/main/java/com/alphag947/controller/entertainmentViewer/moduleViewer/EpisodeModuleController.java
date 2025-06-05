package com.alphag947.controller.entertainmentViewer.moduleViewer;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.controller.ModuleController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class EpisodeModuleController extends ModuleController {

    @FXML private Label module_id;
    @FXML private Label module_name;
    @FXML private Label module_date;

    @FXML private Label status_indicator;
    @FXML private BorderPane module;

    @FXML
    public void view() {
        Episode episode = (Episode) getEntertainment();
        module_id.setText(episode.visualEpisodeNum() + ".");
        module_name.setText(episode.getStageName());

        String stringedDate = (episode.getDate() == null) ? "No Data" : episode.getVisualDate();
        module_date.setText(stringedDate);

        setStatus(module, status_indicator, episode);
    }

    @Override
    public void setEntertainment(Entertainment entertainment) {
        super.setEntertainment(entertainment);
        view();
    }

}
