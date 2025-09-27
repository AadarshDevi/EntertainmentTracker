package com.alphag947.v2.controller.main;

import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.controller.ParentController;
import com.alphag947.v2.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainController extends Controller {
//    @Override
//    public FXMLPackage<Pane, ParentController> getFXML(String basepath, String filename) {
//        return super.getFXML(V2_HOME_2); // FIXME: add custom change
//    }

    @FXML
    public void initialize() {
        System.out.println("Hello NewUI.Home.2");
    }
}
