package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;
import com.alphagnfss.etr3.database.operations.DataOperationInterface;
import com.alphagnfss.etr3.database.operations.DatabaseInterface;
import com.alphagnfss.etr3.database.operations.StorageContainerOperationInterface;

public abstract class Database implements DatabaseInterface, DataOperationInterface, StorageContainerOperationInterface {

    protected Response connect(String uri) {
        return null;
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

    @Override
    public Response disconnect() {
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
