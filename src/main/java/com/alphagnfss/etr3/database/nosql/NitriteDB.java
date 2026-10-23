package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.mvstore.MVStoreModule;

import javax.net.ssl.HttpsURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class NitriteDB extends NoSQLDatabase {
    private Nitrite database = null;

    public Response createDB(Path path) {

        MVStoreModule module = MVStoreModule.withConfig()
                .filePath(path.toString())
                .build();

        this.database = Nitrite.builder()
                .loadModule(module)
                .openOrCreate();

        Response response;

        if (Files.exists(path))
            response = new Response(HttpsURLConnection.HTTP_CREATED);
        else
            response = new Response(HttpsURLConnection.HTTP_BAD_REQUEST, "Unable to create database");

        return response;
    }

    @Override
    public Response createData(Entertainment entertainment) {
        return null;
    }

    @Override
    public Response updateData(String id, Entertainment entertainment) {
        return null;
    }

    @Override
    public Response deleteData(String id) {
        return null;
    }

    @Override
    public Response readData(String id) {
        return null;
    }

    public Response disconnect() {
//        Response response = databaseExists();
//        if (response.getCode() == HttpURLConnection.HTTP_CREATED) {
//            database.close();
//        }
//        return new Response(HttpsURLConnection.HTTP_NOT_FOUND);
        return null;
    }

    @Override
    public Response deleteCollection() {
        return null;
    }

    @Override
    public Response deleteDatabase() {
        return null;
    }

    @Override
    public Response createDatabase() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    public Response connect() {
        return null; // new Response(HttpsURLConnection.HTTP_MOVED_PERM);
    }

    @Override
    public Response createStorage(String storageContainerName) {
        return null;
    }

    @Override
    public Response deleteStorage(String storageContainerName) {
        return null;
    }

    @Override
    public Response hasStorageContainer(String storageContainerName) {
        return null;
    }

}
