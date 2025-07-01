package com.alphag947.controller;

import com.alphag947.api.AppApi;
import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.backend.entertainment.exception.EntertainmentException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ModuleController extends ParentController {

    private int id;
    // private AppApi api;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAppApi(AppApi api) {
        this.api = api;
    }

    public void setStatus(BorderPane module, Label indicator, Entertainment entertainment)
            throws EntertainmentException {
        switch (entertainment.getPrimaryStatus()) {
            case COMPLETED:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_completed");
                indicator.getStyleClass().removeAll();
                indicator.getStyleClass().addAll("status_indicator", "status_indicator_completed");
                break;
            case RELEASED:
            case ONGOING:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_released_ongoing");
                indicator.getStyleClass().removeAll();
                indicator.getStyleClass().addAll("status_indicator", "status_indicator_released_ongoing");
                break;
            case UPCOMING:
                module.getStyleClass().removeAll();
                module.getStyleClass().addAll("module", "module_upcoming");
                indicator.getStyleClass().removeAll();
                indicator.getStyleClass().addAll("status_indicator", "status_indicator_upcoming");
                break;
            default:
                throw new EntertainmentException(entertainment.getPrimaryStatus());
        }
    }

    @FXML
    public void viewEntertainment() throws EntertainmentException {
        api.viewEntertainment(getEntertainment().getId());
    }
}
