package com.alphag947.SceneFactory;

import com.alphag947.api.Api;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SceneManager {

    private Stage stage;
    private static final Logger LOGGER = LogManager.getLogger(SceneManager.class);

    private Rectangle2D screen;

    private int screens;

    public SceneManager() {
        this.stage = null;
//        this.cl = LoggerFactory.getConsoleLogger();

        this.screens = Screen.getScreens().size();
        LOGGER.info("Monitors: " + screens);

        setScreen(0);
    }

    public SceneManager(Stage stage) {
        this.stage = stage;
//        this.cl = LoggerFactory.getConsoleLogger();

        this.screens = Screen.getScreens().size();
        LOGGER.info("Monitors: " + screens);

        setScreen(0);
        setStageSize(0.75, 0.75);
        this.stage.centerOnScreen();
    }

    public void setStageSize(double widthPercent, double heightPercent) {
        String aspectRatio = "16x9";
        if (screen.getWidth() > screen.getHeight()) {
            this.stage.setHeight(screen.getHeight() * heightPercent);
            this.stage.setWidth(screen.getWidth() * widthPercent);

        } else {
            this.stage.setWidth(screen.getWidth() * widthPercent);
            double width = ((screen.getWidth() / 9) * 16) * widthPercent;
            this.stage.setWidth(width);
        }
        this.stage.centerOnScreen();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        setStageSize(0.75, 0.75);
        this.stage.centerOnScreen();
    }

    public void setScene(Scene scene) {
        this.stage.setScene(scene);
    }

    public int getScreenCount() {
        return screens;
    }

    public void setScreen(int screenNum) {
        this.screen = Screen.getScreens().get(screenNum).getBounds();
    }
}
