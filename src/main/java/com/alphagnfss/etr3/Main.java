package com.alphagnfss.etr3;

import com.alphagnfss.etr3.frontend.factory.SVGPaths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    int newEntertainmentId = 0;

    @Override
    public void start(Stage stage) {
        LOGGER.info("Starting Main");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Button("Add", SVGPaths.createSVGPath(SVGPaths.ADD_SVG_PATH)));
        borderPane.setBottom(new Button("Add", SVGPaths.createSVGPath(SVGPaths.ADD_SVG_PATH)));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
