package com.alphag947;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import com.alphag947.CommandLineInterface.CommandLine.CommandLine;
import com.alphag947.SceneFactory.SceneHistory;
import com.alphag947.SceneFactory.SceneManager;
import com.alphag947.api.AppApi;
import com.alphag947.api.AppApiFactory;
import com.alphag947.backend.fxmlLoading.FXMLFactory;
import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.backend.logging.ConsoleLogger;
import com.alphag947.backend.logging.LoggerFactory;
import com.alphag947.controller.uiController.MainframeController;

public class App extends Application {

    private static Stage currentStage;

    private static SceneManager sceneManager;
    private static SceneHistory sceneHistory;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        currentStage = stage;
        sceneHistory = new SceneHistory();

        // create api and console logger
        AppApi api = AppApiFactory.getApi();
        ConsoleLogger cl = LoggerFactory.getConsoleLogger();

        cl.log("AppApi Created");

        // app main ui
        FXMLPackage<BorderPane, MainframeController> mfPackage = FXMLFactory.getFxmlManager().getMainframe();
        BorderPane mf = mfPackage.getPane();
        MainframeController mfc = mfPackage.getController();
        cl.log("Mainframe Created");

        api.setMainframeController(mfc);
        cl.log("AppApi connected to Mainframe");

        // api creates backend and search engine
        api.createBackend();
        api.setBackend();

        cl.log("Frontend: Adding data");
        api.setFrontend();

        Scene homeScene = new Scene(mf);
        sceneManager = new SceneManager(homeScene);

        try {
            setRoot(sceneManager.getScene(0), 0.8, 0.8);
        } catch (Exception e) {
            cl.err(e);
        }

        currentStage.setTitle("Entertainment Tracker");
        currentStage.setResizable(true);
        currentStage.centerOnScreen();
        currentStage.show();
        cl.log("App Running");

        // Create CommandLineInterface to get data from App
        CommandLine commandLine = new CommandLine();
        Thread cmdThread = new Thread(commandLine);
        cmdThread.start();
    }

    public static void setRoot(Scene scene, double width, double height) {
        sceneHistory.add(scene);
        setStageSize(width, height);
        currentStage.setScene(scene);
    }

    public static void setStageSize(double widthPercentage, double heightPercentqage) {
        currentStage.setWidth(Screen.getPrimary().getBounds().getWidth() * widthPercentage);
        currentStage.setHeight(Screen.getPrimary().getBounds().getHeight() * heightPercentqage);
        currentStage.centerOnScreen();
    }

    public static Stage getStage() {
        return currentStage;
    }
}