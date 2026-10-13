package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.database.DatabaseInterface;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.nio.file.Path;

public class MongoDB implements DatabaseInterface {
    private MongoClient mongoClient;

    /**
     * Creates a new connection (database) in MongoDB.
     *
     * @param path Database Path
     * @return database was created or not
     */
    @Override
    public Response createDB(Path path) {
        MongoClient mongoClient = MongoClients.create(path.toString());
        return null;
    }

    @Override
    public Response deleteDB(Path path) {
        return null;
    }

    @Override
    public Response connect() {
        return null;
    }

    @Override
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
