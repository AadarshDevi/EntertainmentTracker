package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.database.operations.DataOperationInterface;
import com.alphagnfss.etr3.database.operations.DatabaseInterface;
import com.alphagnfss.etr3.database.operations.StorageContainerOperationInterface;

public abstract class Database
        implements DatabaseInterface, DataOperationInterface, StorageContainerOperationInterface {
}
