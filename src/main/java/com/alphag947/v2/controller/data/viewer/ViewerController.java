package com.alphag947.v2.controller.data.viewer;

import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.controller.ParentController;
import com.alphag947.v2.controller.data.DataController;
import javafx.scene.layout.Pane;

public class ViewerController extends DataController {
    @Override
    public FXMLPackage<Pane, ParentController> getFXML(String basepath, String filename) {
        return null;
    }
}
