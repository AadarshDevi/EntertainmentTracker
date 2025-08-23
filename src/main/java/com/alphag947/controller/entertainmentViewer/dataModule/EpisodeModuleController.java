package com.alphag947.controller.entertainmentViewer.dataModule;

import com.alphag947.App;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Episode;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.controller.ModuleController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.Logger;

public class EpisodeModuleController extends ModuleController {

    private final Logger LOGGER = Logger.getLogger(EpisodeModuleController.class);

    @FXML
    private Label module_id;
    @FXML
    private Label module_name;
    @FXML
    private Label module_date;

    @FXML
    private Label status_indicator;
    @FXML
    private BorderPane module;

    @FXML
    public void setData() {
        Episode episode = (Episode) getEntertainment();
        module_id.setText(episode.visualEpisodeNum());
        module_name.setText(episode.getStageName());

        String stringedDate = (episode.getDate() == null) ? "No Data" : episode.getVisualDate();
        module_date.setText(stringedDate);

        try {
            setStatus(module, status_indicator, episode);
            // FIXME: Use correct exception
        } catch (EntertainmentNotFoundException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void setEntertainment(Entertainment entertainment) {
        super.setEntertainment(entertainment);
        setData();
    }

    @FXML
    public void viewData() {
        Episode episode = (Episode) getEntertainment();
        if (App.DEBUG) cl.dbg("Viewing: " + getEntertainment().getStageName());
        api.viewEntertainment(episode);
    }
}
