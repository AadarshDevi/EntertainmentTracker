package com.alphagnfss.etr3;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.io.IOException;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {
        LOGGER.info("Starting Main");

        // Step 1: Install MongoDB Compass
        // Step 2:
        // 		- Win11: open "services.msc" and check if MongoDB Server is running
        // Step 3: connect

        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoClient.getDatabase("admin").runCommand(new Document("ping", 1));
            System.out.println("Connected to MongoDB");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to MongoDB");
        }
    }
}