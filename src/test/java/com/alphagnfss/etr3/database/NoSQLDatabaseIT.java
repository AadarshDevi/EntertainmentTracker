package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.database.nosql.MongoDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.HttpURLConnection;

public class NoSQLDatabaseTest {

    private MongoDB database;

    @BeforeEach
    public void setup() {

        // Connect ot MongoDB
        String uri = "mongodb://localhost:27017/EntertainmentTracker_MongoDB_Test_1?connectTimeoutMS=5000&serverSelectionTimeoutMS=5000";
        database = new MongoDB(uri);

        Response response = database.connect();

        if (response.getCode() == HttpURLConnection.HTTP_UNAVAILABLE) {
            System.out.println("Unable to connect to mongodb");
        }

        // Create "data" Collection
    }

    @AfterEach
    public void teardown() {
        database.deleteCollection();
        database.disconnect();
    }

    @Nested
    class CreateDataIT {

        @BeforeEach
        public void localSetup() {

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

            assertTrue((response.getCode() == HttpsURLConnection.HTTP_OK), "Data not created");

            response = database.readData((String) response.getObject());

// 
        }

    }

}
