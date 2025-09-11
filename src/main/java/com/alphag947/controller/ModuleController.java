package com.alphag947.controller;

import com.alphag947.api.Api;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.exception.EntertainmentNotFoundException;
import com.alphag947.backend.entertainment.exception.EntertainmentStatusNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ModuleController extends ParentController {

    private final String oldui_css_filepath = "/com/alphag947/v1/css/module_viewer/status_indicator.css";
    private final String newui_css_filepath = "/com/alphag947/v2/css/data/module/new_status_indicator.css";
    private final String cssFilepath = newui_css_filepath;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppApi(Api api) {
        this.api = api;
    }

    public void setStatus(BorderPane module, Label indicator, Entertainment entertainment)
            throws EntertainmentStatusNotFoundException {
        switch (entertainment.getPrimaryStatus()) {
            case COMPLETED:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_completed");
                indicator.getStyleClass().removeAll();
                indicator.getStyleClass().addAll("status_indicator", "status_indicator_completed");
                break;
            case RELEASED:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_released");
                indicator.getStyleClass().removeAll();
                indicator.getStyleClass().addAll("status_indicator", "status_indicator_released_ongoing");
                break;
            case ONGOING:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_ongoing");
                indicator.getStyleClass().removeAll();
                if (cssFilepath.equals(newui_css_filepath))
                    indicator.getStyleClass().addAll("status_indicator", "status_indicator_upcoming");
                else
                    indicator.getStyleClass().addAll("status_indicator", "status_indicator_released_ongoing");
                break;
            case UPCOMING:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_upcoming");
                indicator.getStyleClass().removeAll();
                indicator.getStyleClass().addAll("status_indicator", "status_indicator_upcoming");
                break;
            default:
                throw new EntertainmentStatusNotFoundException(entertainment.getPrimaryStatus());
        }
    }

    @FXML
    public void viewEntertainment() throws EntertainmentNotFoundException {
        // FIXME: Use better error handling
        // use try catch
        api.viewEntertainment(getEntertainment().getId());
    }

    public String getCssFilepath() {
        return cssFilepath;
    }
}
