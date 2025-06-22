package com.alphag947.controller;

import com.alphag947.backend.entertainment.Entertainment;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ModuleController extends ParentController {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStatus(BorderPane module, Label indicator, Entertainment entertainment) {
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
                cl.err(new Exception("Primary Status \"" + entertainment.getPrimaryStatus() + "\" does not exist."));
        }
    }
}
