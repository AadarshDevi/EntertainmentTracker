package com.alphag947.controller.entertainmentViewer.moduleViewer;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.controller.ModuleController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MovieModuleController extends ModuleController {

    @FXML private BorderPane module;
    @FXML private Label module_id;
    @FXML private Label module_name;
    @FXML private Label module_info_left;
    @FXML private Label module_info_right;

    @FXML private Label status_indicator;

    @FXML
    private void view() {
        Movie movie = (Movie) getEntertainment();
        module_id.setText(super.getId() + ".");
        module_name.setText(movie.getStageName());
        module_info_left.setText(movie.getVisualDuration());
        module_info_right.setText(movie.getVisualDate());

        setStatus(module, status_indicator, movie);
    }

    @Override
    public void setEntertainment(Entertainment entertainment) {
        super.setEntertainment(entertainment);
        view();
    }

}