package com.alphagnfss.etr3;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.database.nosql.MongoDB;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {
        LOGGER.info("Starting Main");


        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        MongoDB database = new MongoDB(uri);
        database.connect();
        database.disconnect();

        System.exit(0);
    }
}