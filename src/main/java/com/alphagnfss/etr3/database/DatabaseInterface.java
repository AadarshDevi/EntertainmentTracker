package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;

import java.nio.file.Path;

/**
 * Operations all database classes need to CRUD Operations
 */
public interface DatabaseInterface {


    Response createDB(Path path);

    Response deleteDB(Path path);

    /**
     * Connect to a database
     *
     * @return connection successful
     */
    Response connect();

    /**
     * Disconnect from a database
     *
     * @return disconnection successful
     */
    Response disconnect();

    /**
     *
     * @param entertainment
     * @return
     */
    Response createData(Entertainment entertainment);

    /**
     * Read data from the database
     *
     * @param id
     * @return
     */
    Response readData(int id);

    Response updateData(Entertainment entertainment);

    Response deleteData(int id);

}
