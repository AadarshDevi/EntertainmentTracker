package com.alphagnfss.etr3;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.data.Episode;
import com.alphagnfss.etr3.data.Movie;
import com.alphagnfss.etr3.data.Show;
import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.database.nosql.MongoDB;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonObjectId;

public class Main extends Application {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    int newEntertainmentId = 0;

    @Override
    public void start(Stage stage) {
        LOGGER.info("Starting Main");

        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        MongoDB client = new MongoDB(uri);
        Response response = client.connect();

        // create Show
        Entertainment show = Show.builder()
                .title("Avatar: The Last Airbender")
                .type(EntertainmentType.TV_SHOW)
                .status(EntertainmentStatus.RELEASED)
                .build();

        // create movie
        Entertainment movie = Movie.builder()
                .title("Toy Story 5")
                .type(EntertainmentType.MOVIE)
                .status(EntertainmentStatus.COMPLETED)
                .build();

        // create episode
        Entertainment episode = Episode.builder()
                .title("The Boy in the Iceberg.")
                .type(EntertainmentType.EPISODE)
                .status(EntertainmentStatus.RELEASED)
                .episodeNum(1)
                .build();

        BsonObjectId showId = (BsonObjectId) client.createData(show).getObject();
        BsonObjectId movieId = (BsonObjectId) client.createData(movie).getObject();
        BsonObjectId episodeId = (BsonObjectId) client.createData(episode).getObject();

        // print ids
        System.out.println("Show: " + showId.getValue());
        System.out.println("Movie: " + movieId.getValue());
        System.out.println("Episode: " + episodeId.getValue());

        try {
            Thread.sleep(7_500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // read data as in JSON
        client.readData(showId);
        client.readData(movieId);
        client.readData(episodeId);


        client.disconnect();
        System.exit(0);
    }
}