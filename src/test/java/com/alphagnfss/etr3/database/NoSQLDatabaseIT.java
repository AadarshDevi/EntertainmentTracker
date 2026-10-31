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

    private MongoDB database = null;

    @BeforeEach
    public void globalSetup() {
        // Connect ot MongoDB
        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        database = new MongoDB(uri);
        this.databaseConnectionResponse = database.connect();
        Response databaseConnectionResponse = database.connect();
    }

    @AfterEach
    public void globalTeardown() {
        database.deleteCollection();
        database.disconnect();
    }

    @Nested
    class CreateDataIT {

        @BeforeEach
        public void localSetup() {
            assumeTrue(database.isConnected(), "Unable to connect to database");
        }

        @AfterEach
        public void localTeardown() {

        }

        @Test
        public void createMovie() {
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

            assertEquals(queryEntertainment.getTitle(), entertainment.getTitle(), "Movie title is not the same.");
            assertSame(queryEntertainment.getStatus(), entertainment.getStatus(), "Movie status is not the same.");
            assertSame(queryEntertainment.getType(), entertainment.getType(), "Movie type is not the same.");
        }

    }

}
