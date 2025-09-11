package com.alphag947.controller.entertainmentViewer.dataModule;

import com.alphag947.App;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.Movie;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.controller.ModuleController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MovieModuleController extends ModuleController {

    @FXML
    private BorderPane module;
    @FXML
    private Label module_id;
    @FXML
    private Label module_name;
    @FXML
    private Label module_info_left;
    @FXML
    private Label module_info_right;

    @FXML
    private Label status_indicator;

    @FXML
    public void initialize() {
        module.getStylesheets().removeAll(module.getStylesheets());
        module.getStylesheets().add(getClass().getResource(getCssFilepath()).toExternalForm());
    }

    @FXML
    public void viewData() throws EntertainmentNotFoundException {
        if (App.DEBUG) {
            cl.bl();
            cl.log(this, getEntertainment().getStageName());
        }
        api.viewEntertainment(getEntertainment().getId());
    }

    private void setData() {
        Movie movie = (Movie) getEntertainment();
        module_id.setText(super.getId() + ".");
        module_name.setText(movie.getStageName());
        module_info_left.setText(movie.getVisualDuration());
        module_info_right.setText(movie.getVisualDate());

        try {
            setStatus(module, status_indicator, movie);
            // FIXME: correct exception
        } catch (EntertainmentNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setEntertainment(Entertainment entertainment) {
        super.setEntertainment(entertainment);
        setData();
    }
}