package com.alphag947.v2.controller.utli;

import com.alphag947.App;
import com.alphag947.backend.fxmlLoading.ControllerNullException;
import com.alphag947.backend.fxmlLoading.URLNullException;
import com.alphag947.v2.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class FXMLReader {

    public static final Logger LOGGER = Logger.getLogger(FXMLReader.class);
    public static final String V2_HOME = "/com/alphag947/v2/fxml/base/base_v2.fxml";
    private static FXMLReader fxmlReader = null;

    public static FXMLReader getInstance() {
        if (fxmlReader == null) fxmlReader = new FXMLReader();
        return fxmlReader;
    }

    public FXMLPackage getFXML(String filepath) {
        try {
            URL url = FXMLReader.class.getResource(filepath);

            if (url == null) throw new URLNullException(filepath);
            FXMLLoader loader = new FXMLLoader(url);

            if (App.DEBUG) LOGGER.info("FXMLLoader: " + loader);
            Pane pane = loader.load();

            if (App.DEBUG) LOGGER.info("Pre-Pane: " + pane);
            Controller controller = loader.getController();

            if (App.DEBUG) LOGGER.info("Pre-Package Controller: " + controller);
            pane.getProperties().put("controller", controller);

            if (controller == null) throw new ControllerNullException(filepath);

            return new FXMLPackage(pane, controller);
        } catch (URLNullException e) {
            LOGGER.error("URL for fxml does not exist.");
        } catch (ControllerNullException e) {
            LOGGER.error("FXML Controller is null");
        } catch (IOException e) {
            LOGGER.error("Unable to get FXML.");
        }
        return null;
    }
}
