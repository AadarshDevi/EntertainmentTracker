package com.alphag947.backend.fxmlLoading;

import com.alphag947.controller.ParentController;

import javafx.scene.layout.Pane;

public class FXMLPackage<T extends Pane, C extends ParentController> {

    private T pane;
    private C controller;

    public FXMLPackage(T pane, C controller) {
        this.pane = pane;
        this.controller = controller;
    }

    public T getPane() {
        return pane;
    }

    public C getController() {
        return controller;
    }

}
