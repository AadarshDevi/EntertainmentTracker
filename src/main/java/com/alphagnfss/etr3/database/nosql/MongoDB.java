package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.net.ssl.HttpsURLConnection;
import java.nio.file.Path;

/**
 * Connecting to MongoDB:
 * - Step 1: Install MongoDB Compass
 * - Step 2: (Win11) open "services.msc" and check if MongoDB Server is running.
 * - Step 3: connect
 * <p>
 * Server/Database --> Collection (Table) --> Document (Row)
 * <p>
 * Data in Server:
 * - Step 1: Movie movie = new Movie(...);
 * - Step 2: add Movie (A Doc)
 */
public class MongoDB extends NoSQLDatabase {
    private final String DOCUMENT_NAME_DATA = "data";
    private final String DOCUMENT_NAME_COLLECTION = "collection";
    private String connection;
    private MongoClient mongoClient;
    private Response response;

    public MongoDB(String connection) {
        this.connection = connection;
    }

    public Response createDB(Path path) {
        return null;
    }

    public Response deleteDB(Path path) {
        return null;
    }

    private Response connect(String uri) {
        try {
            mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyConnectionString(new ConnectionString(uri))
                            .build()
            );
            mongoClient.listDatabaseNames().forEach(System.out::println);
            mongoClient.getDatabase("admin").runCommand(new Document("ping", 1));
            System.out.println("Connected to MongoDB!");

            database = mongoClient.getDatabase(databases.get(0));
            System.out.println("Database Connected: " + database.getName());
        } catch (Exception e) {
            return new Response(HttpsURLConnection.HTTP_UNAVAILABLE, e.getMessage());
        }
        return new Response(HttpsURLConnection.HTTP_OK);
    }

    public Response connect() {
        return connect(connection);
    }

    public Response disconnect() {
        mongoClient.close();
        return new Response(HttpsURLConnection.HTTP_OK);
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
