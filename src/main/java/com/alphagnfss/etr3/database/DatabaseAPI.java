package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

public class DatabaseAPI implements DatabaseInterface {

    @Setter
    @Getter
    private DatabaseInterface database;

    public DatabaseAPI(DatabaseInterface database) {
        this.database = database;
    }

    @Override
    public Response createDB(Path path) {
        return database.createDB(path);
    }

    @Override
    public Response deleteDB(Path path) {
        return null;
    }

    @Override
    public Response connect() {
        return database.connect();
    }

    @Override
    public Response disconnect() {
        return database.disconnect();
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
