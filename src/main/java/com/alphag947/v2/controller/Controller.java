package com.alphag947.v2.controller;

import com.alphag947.App;
import com.alphag947.backend.fxmlLoading.ControllerNullException;
import com.alphag947.backend.fxmlLoading.FXMLManager;
import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.backend.fxmlLoading.URLNullException;
import com.alphag947.controller.ParentController;
import com.alphag947.v2.controller.data.FXMLReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public abstract class Controller implements FXMLReader {

    private final Logger LOGGER = Logger.getLogger(Controller.class);

    public FXMLPackage<Pane, ParentController> getFXML(String filepath) {
        try {
            URL url = FXMLManager.class.getResource(filepath);
            if (url == null)
                throw new URLNullException(filepath);
            FXMLLoader loader = new FXMLLoader(url);
            if (App.DEBUG) LOGGER.info("FXMLLoader: " + loader);
            Pane pane = loader.load();
            if (App.DEBUG) LOGGER.info("Pre-Pane: " + pane);
            // TODO: Convert to Controller and
            ParentController controller = loader.getController();
            if (App.DEBUG) LOGGER.info("Pre-Package Controller: " + controller);
            pane.getProperties().put("controller", controller);
            if (controller == null)
                throw new ControllerNullException(filepath);
            return new FXMLPackage<>(pane, controller);
        } catch (URLNullException | IOException | ControllerNullException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
