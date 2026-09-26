package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.database.nosql.NitriteDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import java.nio.file.Path;

public class DatabaseTest {
    //    private NitriteDB nitriteDB; // = new NitriteDB();
    private DatabaseAPI databaseAPI; // = new DatabaseAPI(nitriteDB);

    @BeforeEach
    public void setup() {
//        nitriteDB = ;
        databaseAPI = new DatabaseAPI(new NitriteDB());
    }

    @AfterEach
    public void teardown() {
        databaseAPI.disconnect();
    }

    @Test
    public void createDBTest() {

        Assertions.assertEquals(
                HttpsURLConnection.HTTP_CREATED,
                databaseAPI.createDB(Path.of("C:/Users/CryosArtic/IdeaProjects/EntertainmentTracker/src/test/java/com/alphagnfss/etr3/database.db")).getCode()
        );
    }

}
