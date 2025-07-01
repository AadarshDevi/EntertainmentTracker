package com.alphag947.controller.entertainmentViewer.infoViewer;

import com.alphag947.backend.entertainment.Entertainment;
import com.alphag947.controller.ViewerController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MovieViewerController extends ViewerController {
    @FXML private VBox viewer;
    @FXML private Label e_type;

    public void view(Entertainment entertainment) {
        e_type.setText(entertainment.getStageName());
    }
}
