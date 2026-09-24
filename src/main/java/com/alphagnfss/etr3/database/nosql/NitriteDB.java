package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.database.DatabaseInterface;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.mvstore.MVStoreModule;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.nio.file.Path;

public class NitriteDB implements DatabaseInterface {
    private Nitrite database = null;

    @Override
    public Response createDB(Path path) {

        MVStoreModule module = MVStoreModule.withConfig()
                .filePath(path.toString())
                .build();

        this.database = Nitrite.builder()
                .loadModule(module)
                .openOrCreate();

        Response response = databaseExists();
        return (response.getCode() == HttpURLConnection.HTTP_BAD_REQUEST) ?
                response :
                new Response(HttpsURLConnection.HTTP_CREATED);
    }

    @Override
    public Response deleteDB(Path path) {
        return null;
    }

    @Override
    public Response deleteData(int id) {
        return null;
    }

    @Override
    public Response updateData(Entertainment entertainment) {
        return null;
    }

    @Override
    public Response readData(int id) {
        return null;
    }

    @Override
    public Response createData(Entertainment entertainment) {
        return null;
    }

    @Override
    public Response disconnect() {
        return null;
    }

    @Override
    public Response connect() {
        return null;
    }

    public Response databaseExists() {
        if (database == null)
            return new Response(HttpsURLConnection.HTTP_BAD_REQUEST, "Database Not Found", null);
        return new Response(HttpsURLConnection.HTTP_CREATED);
    }
}
