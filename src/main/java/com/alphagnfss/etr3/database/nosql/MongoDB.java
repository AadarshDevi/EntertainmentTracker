package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.database.DatabaseInterface;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.net.ssl.HttpsURLConnection;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;

public class MongoDB extends NoSQLDatabase implements DatabaseInterface {
    private final String connection;
    private final Vector<String> databases = new Vector<>(List.of(
            "data", "collection"
    ));
    private MongoClient mongoClient;
    private MongoDatabase database;
    private Response response;

    public MongoDB(String connection) {
        this.connection = connection;
    }

    public Response createDB(Path path) {
//        mongoClient = MongoClients.create(path.toString());
//        System.out.println(mongoClient);
        return null;
    }

    public Response deleteDB(Path path) {
        return null;
    }

    public Response connect() {
        return null;
    }

    public Response disconnect() {
        return null;
    }

    @Override
    public Response createData(Entertainment entertainment) {
        return null;
    }

    @Override
    public Response readData(int id) {
        return null;
    }

    @Override
    public Response updateData(Entertainment entertainment) {
        return null;
    }

    @Override
    public Response deleteData(int id) {
        return null;
    }
}
