package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.communication.ResponseCode;
import com.alphagnfss.etr3.communication.Responses;
import com.alphagnfss.etr3.data.*;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import com.alphagnfss.etr3.database.DataGroup;
import com.alphagnfss.etr3.database.SearchQuery;
import com.alphagnfss.etr3.validation.InputValidation;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonParseException;
import org.bson.types.ObjectId;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    private final String databaseName;
    private String connection;
    private MongoClient mongoClient;

    public MongoDB(String connection, String databaseName) {
        this.connection = connection;
        this.databaseName = databaseName;
    }

    public Response createDB(Path path) {
        return null;
    }

    public Response deleteDB(Path path) {
        return null;
    }

    protected Response connect(String uri) {
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
                return Response.builder().responseCode(Responses.DATABASE_CONNECTION_ERROR).build();
            }

        } catch (Exception e) {
            return Response.builder().responseCode(new ResponseCode(Responses.DATABASE_CONNECTION_ERROR.code(), e.getMessage())).build();
        }
        return new Response(Responses.SUCCESS);
    }

    public boolean isConnected() {
        if (mongoClient == null) return false;
        try {
            mongoClient.getDatabase("admin").runCommand(new Document("ping", 1));
            return true;
        } catch (MongoException | IllegalStateException e) {
            return false;
        }
    }

    @Override
    public Response connect() {
        return connect(connection);
    }

    @Override
    public Response disconnect() {
        if (mongoClient == null) return Response.builder().responseCode(Responses.SUCCESS).build();
        try {
            mongoClient.close();
            return new Response(Responses.SUCCESS);
        } catch (MongoException | IllegalStateException e) {
            return new Response(Responses.DATABASE_DISCONNECTION_ERROR);
        }
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
    public Response createData(Entertainment entertainment) {

        final Response response = Response.builder().build();

        // Get Collection
        MongoCollection<Document> collection = getCollection(DataGroup.DATA);

        // Java Object --> JSON Strings
        ObjectMapper mapper = JsonMapper.builder().build();

        // EntertainmentObject Serialized
        String string = mapper.writeValueAsString(entertainment);

        // Print JSON String
        System.out.println(string);

        // JSON String to MongoDB Document
        Document document = Document.parse(string);

        // Inserts the Document in the Collection
        InsertOneResult result = collection.insertOne(document);

        // Check if writing the Document was not successful
        if (!result.wasAcknowledged()) {
            return Response.builder().responseCode(Responses.DATABASE_DATA_CREATE_ERROR).build();
        }

        String returnId = result.getInsertedId().asObjectId().getValue().toHexString();

        // Document was written successfully
        response.setResponseCode(Responses.SUCCESS);
        response.setObject(returnId);

        // print object's id
        System.out.println("Successfully Created Data: Object ID - " + response.getObject());

        // return response
        return response;
    }

    @Override
    public Response readData(String id) {

        final Response response = Response.builder().build();

        // Get Collection
        MongoCollection<Document> collection = getCollection(DataGroup.DATA);

        // Create a Document
        Document searchResult = collection.find(new Document("_id", new ObjectId(id))).first();

        if (searchResult == null) {
            response.setResponseCode(Responses.DATABASE_DATA_READ_ERROR);
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
            EntertainmentType type = jsonNode.has("type")
                    ? EntertainmentType.valueOf(
                    jsonNode.get("type").toString().replace("\"", ""))
                    : EntertainmentType.NONE;

            // Get Entertainment Object but getting the type and then deserializing it to get the correct subclass
            Entertainment entertainment = switch (type) {
                case MOVIE -> mapper.readValue(json, Movie.class);
                case TV_SHOW, ANIME -> mapper.readValue(json, Show.class);
                case EPISODE -> mapper.readValue(json, Episode.class);
                default -> mapper.readValue(json, Placeholder.class);
            };
            entertainment.setEntertainmentId(id);

            // Build Response and send entertainment object
            response.setResponseCode(Responses.SUCCESS);
            response.setObject(entertainment);

        } catch (JsonParseException e) {
            response.setResponseCode(new ResponseCode(Responses.BACKEND_PARSER_ERROR.code(), e.getMessage()));
        }

        return response;
    }

    @Override
    public Response deleteData(String id) {

        final Response response = Response.builder().build();

        try {
            // Get Collection
            MongoCollection<Document> collection = getCollection(DataGroup.DATA);

            // Filter to find the right Entertainment Object
            Bson filter = Filters.eq("_id", new ObjectId(id));

            // Use filter to delete the data of the specific Entertainment Object
            DeleteResult deleteResult = collection.deleteOne(filter);

            if (!deleteResult.wasAcknowledged()) {
                return Response.builder().responseCode(Responses.DATABASE_DATA_DELETE_ERROR).build();
            }

            if (deleteResult.getDeletedCount() <= 0) {
                return Response.builder().responseCode(Responses.DATABASE_DATA_DELETE_ERROR).build();
            }

            response.setResponseCode(Responses.SUCCESS);

        } catch (Exception e) {
            return Response.builder().responseCode(new ResponseCode(Responses.DATABASE_ERROR.code(), e.getMessage())).build();
        }

        return response;
    }

    @Override
    public Response updateData(String id, Entertainment entertainment) {

        final Response response = Response.builder().build();

        try {
            // Get Collection
            MongoCollection<Document> collection = getCollection(DataGroup.DATA);

            // Java Object --> JSON Strings
            ObjectMapper mapper = JsonMapper.builder().build();

            // EntertainmentObject Serialized
            String string = mapper.writeValueAsString(entertainment);

            // Document that contains the new updated version of the Entertainment Object
            Document replacingDocument = Document.parse(string);

            // Filter: Get Document with field "_id
            Bson filters = Filters.eq("_id", new ObjectId(id));

            // Create a Replace Document Object
            ReplaceOptions options = new ReplaceOptions().upsert(false);

            // Use filter to find old doc and then use options to replace the old doc with the new doc
            UpdateResult result = collection.replaceOne(filters, replacingDocument, options);

            // Check if the update failed
            if (!result.wasAcknowledged()) {
                return Response.builder().responseCode(Responses.DATABASE_DATA_UPDATE_ERROR).build();
            }

            response.setResponseCode(Responses.SUCCESS);

        } catch (JacksonException e) {
            response.setResponseCode(new ResponseCode(Responses.BACKEND_PARSER_ERROR.code(), e.getMessage()));
        }

        return response;
    }

    private MongoCollection<Document> getCollection(String dataGroup) {
        // get database from server
        MongoDatabase database = mongoClient.getDatabase(databaseName);

        // get collection from server
        MongoCollection<Document> collection = database.getCollection(dataGroup);
        return collection;
    }

    @Override
    public Response deleteCollection() {
        final Response response = Response.builder().build();
        mongoClient.getDatabase(databaseName).getCollection(DataGroup.DATA).drop();
        response.setResponseCode(Responses.SUCCESS);
        return response;
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

    public Response searchData(SearchQuery searchQuery) {

        List<Bson> filters = new ArrayList<>();

        if (Responses.deepEquals(InputValidation.validString(searchQuery.title()), Responses.SUCCESS))
            filters.add(Filters.regex("title", Pattern.quote(searchQuery.title()), "i"));
        if (Responses.deepEquals(InputValidation.validType(searchQuery.type().name()), Responses.SUCCESS))
            filters.add(Filters.eq("type", searchQuery.type().name()));
        if (EntertainmentInputValidation.validStatus(searchQuery.status()))
            filters.add(Filters.eq("status", searchQuery.status().name()));
        if (EntertainmentInputValidation.validType(searchQuery.type()))
            filters.add(Filters.eq("type", searchQuery.type().name()));


        return null;
    }
}
