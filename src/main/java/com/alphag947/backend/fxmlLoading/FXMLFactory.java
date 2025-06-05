package com.alphag947.backend.fxmlLoading;

import javafx.scene.layout.Pane;

public class FXMLFactory {
    private static FXMLManager<? extends Pane, ?> manager;

    public static FXMLManager<? extends Pane, ?> getFxmlManager() {
        if (manager == null)
            manager = new FXMLManager<>();
        return manager;
    }
}
