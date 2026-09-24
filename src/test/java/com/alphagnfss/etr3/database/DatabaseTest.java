package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.database.nosql.NitriteDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import java.nio.file.Path;

public class DatabaseTest {
    private final DatabaseAPI databaseAPI = new DatabaseAPI(new NitriteDB());

    @Test
    public void createDBTest() {

        Assertions.assertEquals(
                HttpsURLConnection.HTTP_CREATED,
                databaseAPI.createDB(Path.of("../../../../../../../database.nosql")).getCode()
        );
    }

}
