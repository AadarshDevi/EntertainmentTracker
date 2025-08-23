package com.alphag947.controller.uiController.excepsion;

import com.alphag947.controller.ParentController;
import javafx.scene.layout.Pane;

public class UIException extends RuntimeException {
    public UIException(String message) {
        super(message);
    }

//    public UIException(Pane p) {
//        super(String.format("UI Component %s is null", p.getClass()));
//    }
//
//    public UIException(Class<T> class) {
//        super(String.format("UI Component %s is null", pc.getClass()));
//    }

    public UIException() {
        super("UI Component or UIController is null");
    }
}
