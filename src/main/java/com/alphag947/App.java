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

/**
 * C:\Users\{{user.name}}\AppData\Local\AlphaGeN_Studios
 *
 * @author AlpharinoGamer947
 * @date May 25, 2025
 */
public class App extends Application {

    public static final boolean DEBUG = false;

    public static final boolean TESTING = true;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch();
    }

    @Override
    public void start(Stage stage) {

        // the logger for App.start()
        final Logger LOGGER = LogManager.getLogger(App.class);


        Api api = ApiFactory.getApi();
        api.readFilePath("filepath"); // TODO: read settings file
        api.getSceneManager().setStage(stage);
        LOGGER.info("Api Created");

        BorderPane mf = null;
        MainframeController mfc = null;

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

        api.createBackend();
        api.setBackend();
        LOGGER.info("Backend: Ready");

        api.setFrontend();
        LOGGER.info("Frontend: Added data");

        stage.setScene(new Scene(mf));
        stage.setTitle("Entertainment Tracker");
        stage.setResizable(true);
        stage.centerOnScreen();

//        api.test();

        stage.show();
        LOGGER.info("App Running");

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