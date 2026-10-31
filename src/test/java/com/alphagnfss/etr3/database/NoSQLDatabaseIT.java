package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.communication.Responses;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.data.Movie;
import com.alphagnfss.etr3.data.enums.EntertainmentStatus;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.database.nosql.MongoDB;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class NoSQLDatabaseIT {

    private static MongoDB database = null;

    @BeforeAll
    public static void globalSetup() {
        // Connect ot MongoDB
        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        database = new MongoDB(uri);

        Response databaseConnectionResponse = database.connect();
        assertEquals(Responses.SUCCESS, databaseConnectionResponse.getResponseCode(), "Connection failed");
        assertTrue(database.isConnected(), "Unable to connect to database");

    }

    @AfterAll
    public static void globalTeardown() {
        database.deleteCollection();
        database.disconnect();
    }

    @Nested
    class CreateAndReadDataIT {

        @BeforeEach
        public void localSetup() {
            assumeTrue(database.isConnected(), "Unable to connect to database");
        }

        @AfterEach
        public void localTeardown() {

        }

        @Test
        public void createAndReadMovie() {
            Entertainment entertainment = Movie.builder()
                    .title("Avatar Aang: The Last Airbender")
                    .status(EntertainmentStatus.RELEASED)
                    .type(EntertainmentType.MOVIE)
                    .tag("Avatar movie")
                    .build();

            Response response = database.createData(entertainment);
            assertEquals(Responses.SUCCESS, response.getResponseCode(), "Data not created");

            response = database.readData((String) response.getObject());
            assertEquals(Responses.SUCCESS, response.getResponseCode(), "Unable to read data");

            Entertainment queryEntertainment = (Entertainment) response.getObject();
            assertEquals(entertainment.getTitle(), queryEntertainment.getTitle(), "Movie title is not the same.");
            assertSame(entertainment.getStatus(), queryEntertainment.getStatus(), "Movie status is not the same.");
            assertSame(entertainment.getType(), queryEntertainment.getType(), "Movie type is not the same.");
        }

        @Test
        public void createAndReadAnime() {
            Entertainment entertainment = Show.builder()
                    .title("Beyblade X Season 4")
                    .status(EntertainmentStatus.ONGOING)
                    .type(EntertainmentType.ANIME)
                    .tag("beyblade")
                    .tag("beyblade x")
                    .build();
        }

        @Test
        public void createAndReadShow() {
            Entertainment entertainment = Show.builder()
                    .title("Avatar: The Last Airbender")
                    .status(EntertainmentStatus.RELEASED)
                    .type(EntertainmentType.TV_SHOW)
                    .tag("avatar")
                    .tag("elements")
                    .build();
        }

        @Test
        public void createAndReadEpisode() {
            Entertainment entertainment = Episode.builder()
                    .title("The Boy in the Iceberg")
                    .status(EntertainmentStatus.COMPLETED)
                    .type(EntertainmentType.EPISODE)
                    .build();
        }

    }

}
