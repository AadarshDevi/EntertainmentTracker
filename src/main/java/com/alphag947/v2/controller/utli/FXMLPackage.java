package com.alphag947.v2.controller.utli;

import com.alphag947.v2.controller.Controller;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class FXMLPackage {

    @Getter
    private Pane pane;

    @Getter
    private Controller controller;

    public FXMLPackage(Pane pane, Controller controller) {
        this.pane = pane;
        this.controller = controller;
    }

}
