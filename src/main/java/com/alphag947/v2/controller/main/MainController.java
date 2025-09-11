package com.alphag947.v2.controller.main;

import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.controller.ParentController;
import com.alphag947.v2.controller.Controller;
import javafx.scene.layout.Pane;

public class MainController extends Controller {
    @Override
    public FXMLPackage<Pane, ParentController> getFXML(String basepath, String filename) {
        return super.getFXML(""); // FIXME: add custom change
    }
}
