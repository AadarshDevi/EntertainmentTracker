package com.alphagnfss.etr3.database.operations;

import com.alphagnfss.etr3.communication.Response;

/**
 * Operations all database classes need to CRUD Operations
 */
public interface DatabaseInterface {

    Response disconnect();

    Response deleteDatabase();

    Response createDatabase();

    boolean isConnected();

}
