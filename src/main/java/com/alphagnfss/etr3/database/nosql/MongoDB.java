package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.database.DataGroup;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

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

    private final Response response = new Response(-1, "");
    private String connection;
    private MongoClient mongoClient;
    private InsertOneResult result;

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

            // connect to mongodb
            mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyConnectionString(new ConnectionString(uri))
                            .build()
            );

            connection = null;

            // check if the connection was established
            if (!isConnected()) {
                return new Response(HttpsURLConnection.HTTP_UNAVAILABLE, "Unable to connect to server");
            }

        } catch (Exception e) {
            return new Response(HttpsURLConnection.HTTP_UNAVAILABLE, e.getMessage());
        }
        return new Response(HttpsURLConnection.HTTP_OK);
    }

    private boolean isConnected() {

        // pingResult = mongoClient.getDatabase("admin").runCommand(new Document("ping", 1))
        // pingValue = pingResult.getDouble("ok")
        Double result = mongoClient.getDatabase("admin").runCommand(new Document("ping", 1)).getDouble("ok");
        return ((result != null) && (result == 1.0));
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
        MongoDatabase database = mongoClient.getDatabase(DataGroup.DATABASE);
        MongoCollection<Document> collection = database.getCollection(DataGroup.DATA);

        ObjectMapper mapper = JsonMapper.builder().build();
        String string = mapper.writeValueAsString(entertainment);
        System.out.println(string);

        Document document = Document.parse(string);

        result = collection.insertOne(document);
        if (!result.wasAcknowledged()) {
            response.setCode(HttpsURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage("Unable to write " + entertainment.getTitle() + "to database");
            return response;
        }

        response.setCode(HttpsURLConnection.HTTP_OK);
        return response;
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

    private MongoDatabase getDatabase(String databaseName) {
        mongoClient.getDatabase(databaseName).runCommand(new Document("ping", 1));
        return mongoClient.getDatabase(databaseName);

    }
}
