package com.alphag947.backend.fxmlLoading;

import com.alphag947.controller.ParentController;
import com.alphag947.v2.controller.utli.FXMLReader;
import javafx.scene.layout.Pane;

public class FXMLFactory {
    private static FXMLManager<? extends Pane, ? extends ParentController> manager;

    public static FXMLManager<? extends Pane, ? extends ParentController> getFxmlManager() {
        if (manager == null) manager = new FXMLManager<>();
        return manager;
    }

}
