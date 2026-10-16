package com.alphagnfss.etr3.database.operations;

import com.alphagnfss.etr3.communication.Response;

public interface StorageContainerOperationInterface {

    Response createStorage(String storageContainerName);

    Response deleteStorage(String storageContainerName);

    Response hasStorageContainer(String storageContainerName);

}
