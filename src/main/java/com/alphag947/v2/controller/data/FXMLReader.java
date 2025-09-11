package com.alphag947.v2.controller.data;

import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.controller.ParentController;
import javafx.scene.layout.Pane;

public interface FXMLReader {
    FXMLPackage<Pane, ParentController> getFXML(String basepath, String filename);
}
