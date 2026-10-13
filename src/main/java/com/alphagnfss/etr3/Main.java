package com.alphagnfss.etr3;

import com.alphagnfss.etr3.database.nosql.MongoDB;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {
        LOGGER.info("Starting Main");


        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        MongoDB database = new MongoDB(uri);
        database.connect();
        database.disconnect();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
        //        try (
//                MongoClient mongoClient = MongoClients.create(
//                        MongoClientSettings.builder()
//                                .applyConnectionString(new ConnectionString(uri))
//                                .build()
//                )
//        ) {
//            mongoClient.getDatabase("admin").runCommand(new Document("ping", 1));
//            System.out.println("Connected to MongoDB");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Failed to connect to MongoDB");
//        }
    }
}