package com.alphag947;

import com.alphag947.api.Api;
import com.alphag947.api.ApiFactory;
import com.alphag947.backend.fxmlLoading.FXMLFactory;
import com.alphag947.backend.fxmlLoading.FXMLPackage;
import com.alphag947.backend.fxmlLoading.exception.FXMLNullException;
import com.alphag947.controller.uiController.MainframeController;
import com.alphag947.controller.uiController.excepsion.UIException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * C:\Users\{{user.name}}\AppData\Local\AlphaGeN_Studios
 *
 * @author AlpharinoGamer947
 * @date May 25, 2025
 */
public class App extends Application {

    /**
     * This is used for logging. If this is true, the logger will not log the information. used
     * for distribution.
     */
    public static final boolean DEBUG = true;

    /**
     * Main method for the app
     *
     * @param args are for cli arguments
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch();
    }

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set. This will go to SceneManager
     *              so all scenes can be managed in a single class.
     */
    @Override
    public void start(Stage stage) {

        // the logger for App.start()
        final Logger LOGGER = LogManager.getLogger(App.class);
        LOGGER.info(System.getProperty("java.version"));

        /*
         * gets the name of the user, it is the login name of the user.
         */
        String user = System.getProperty("user.name");
        LOGGER.info(user);

        /*
         * uses the user to generate the database folder if it does not exist
         */
        File folder = new File("C:/Users/" + user + "/AppData/Local/AlphaGeN_Studios/EntertainmentTracker/database");
        if (!(folder.exists() && folder.isDirectory()))
            folder.mkdirs();
        else LOGGER.info("Directory Exists");

        /*
         * uses the user to generate default settings file if it does not exist
         */
        File settings = new File("C:/Users/" + user + "/AppData/Local/AlphaGeN_Studios/EntertainmentTracker/settings.txt");
        if (!settings.exists()) {
            // TODO: generate default settings folder
            LOGGER.info("Create Settings File");
        }

        /*
         * Creates API that manages all calls between UIControllers, Backend and sometimes the CLI
         * After creating api, the settings app must be read.
         * then the stage must be passed to the SceneManager.
         */
        Api api = ApiFactory.getApi();
        api.readFilePath("filepath"); // TODO: read settings file
        api.getSceneManager().setStage(stage);
        LOGGER.info("Api Created");

        /*
         * The borderpane mf is the MainUI
         */
        BorderPane mf = null;

        /*
         * The MainframeController mfc is the MainController
         */
        MainframeController mfc = null;

        /*
         * Gets the FXMLPackage contains the MainUI and the MainController
         * throws UIException if either are null. This is to prevent searching
         * lines of code to find the error.
         */
        try {
            LOGGER.debug("MainUI URL: " + App.class.getResource("/com/alphag947/v1/fxml/main/mainframe_b2_v1.fxml"));
            FXMLPackage<BorderPane, MainframeController> mfPackage = FXMLFactory.getFxmlManager().getMainframe();
            if (mfPackage == null) throw new FXMLNullException();

            mf = mfPackage.getPane();
            mfc = mfPackage.getController();
            if (mf == null || mfc == null) throw new UIException();
            LOGGER.info("Mainframe Created");
        } catch (UIException e) {
            LOGGER.error(e);
        } catch (FXMLNullException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }

        api.setMainframeController(mfc);
        LOGGER.info("AppApi connected to Mainframe");

        /*
         * the api creates the Backend and the SearchEngine
         * then it starts the backend and readies the SearchEngine
         */
        api.createBackend();
        api.setBackend(settings.getAbsolutePath());
        LOGGER.info("Backend: Ready");

        /*
         * Adds the entertainment data to the MainUI
         */
        api.setFrontend();
        LOGGER.info("Frontend: Added data");

        /*
         * Readies the stage and sets:
         * scene > MainUI,
         * title > Entertainment Tracker
         * resizeable > true
         * stage is on center
         */
        Scene homeScene = new Scene(mf);
        stage.setScene(homeScene);
        stage.setTitle("Entertainment Tracker");
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
        LOGGER.info("App Running");

        /*
         * sets the number of monitor screens so changing the size of app is based on the monitor
         */
        api.getMainframeController().setScreenCount(api.getSceneManager().getScreenCount());

        /*
         * FIXME: The CommandLineInterface is on same thread as the app
         * TODO: (This is part of NewUI) Make a textArea above a textbox so user will able to use
         *          cmd to access data.
         *          To access the cli, in the searchbar(MainUI)/searchtab(NewUI), type @cli enter=true
         *          to be able to use the cli and in the cli, type
         *          @cli exit=true to leave and go back to MainUI.
         * Create a CommandLineInterface to get data from App.
         */
//        CommandLine commandLine = new CommandLine();
//        Thread cmdThread = new Thread(commandLine);
//        cmdThread.start();
    }
}