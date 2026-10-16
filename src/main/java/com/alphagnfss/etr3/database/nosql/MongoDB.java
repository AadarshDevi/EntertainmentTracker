package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.*;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.database.DataGroup;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.json.JsonParseException;
import tools.jackson.databind.JsonNode;
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

        // Internal Response object
        Response response = Response.builder().build();

        // get database from server
        MongoDatabase database = mongoClient.getDatabase(DataGroup.DATABASE);

        // get collection from server
        MongoCollection<Document> collection = database.getCollection(DataGroup.DATA);

        // Java Object --> JSON Strings
        ObjectMapper mapper = JsonMapper.builder().build();

        // EntertainmentObject Serialized
        String string = mapper.writeValueAsString(entertainment);

        // Print JSON String
        System.out.println(string);

        // JSON String to MongoDB Document
        Document document = Document.parse(string);

        // Inserts the Document in the Collection
        result = collection.insertOne(document);

        // Check if writing the Document was not successful
        if (!result.wasAcknowledged()) {
            response.setCode(HttpsURLConnection.HTTP_INTERNAL_ERROR);
            response.setMessage("Unable to write " + entertainment.getTitle() + "to database");
            return response;
        }

        // Document was written successfully
        response.setCode(HttpsURLConnection.HTTP_OK);
        response.setObject(result.getInsertedId().asObjectId());

        // print object's id
        System.out.println("Successfully Created Data: Object ID - " + ((BsonObjectId) response.getObject()).getValue());

        // return response
        return response;
    }

    public Response readData(BsonObjectId id) {

        // Internal Response object
        Response response = Response.builder().build();

        // get database from server
        MongoDatabase database = mongoClient.getDatabase(DataGroup.DATABASE);

        // get collection from server
        MongoCollection<Document> collection = database.getCollection(DataGroup.DATA);

        // Create a Document
        Document searchResult = collection.find(new Document("_id", id.getValue())).first();

        if (searchResult == null) {
            response.setCode(HttpsURLConnection.HTTP_NOT_FOUND);
            return response;
        }

        try {

            // Document to JSON
            String json = searchResult.toJson();

            // JSON --> Object Convertor
            ObjectMapper mapper = JsonMapper.builder().build();

            // Generic JSON Tree
            JsonNode jsonNode = mapper.readTree(json);

            // Get EntertainmentType
            EntertainmentType type = jsonNode.has("type") ? EntertainmentType.valueOf(jsonNode.get("type").toString().replace("\"", "")) : EntertainmentType.NONE;

            // Get Entertainment Object but getting the type and then deserializing it to get the correct subclass
            Entertainment entertainment = switch (type) {
                case MOVIE -> mapper.readValue(json, Movie.class);
                case TV_SHOW, ANIME -> mapper.readValue(json, Show.class);
                case EPISODE -> mapper.readValue(json, Episode.class);
                default -> mapper.readValue(json, Placeholder.class);
            };
            entertainment.setEntertainmentId(id.getValue().toHexString());

            // Build Response and send entertainment object
            response.setCode(HttpsURLConnection.HTTP_OK);
            response.setObject(entertainment);

        } catch (JsonParseException e) {
            e.printStackTrace();
        }

        return response;
    }

    public Response updateData(BsonObjectId id) {
        return null;
    }

    @Override
    public Response deleteData(int id) {
        return null;
    }

    private MongoDatabase getDatabase(String databaseName) {
        return mongoClient.getDatabase(databaseName);
    }

    public Response updateData(BsonObjectId id1, BsonObjectId id2) {
        return null;
    }
}
