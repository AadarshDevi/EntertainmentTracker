package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.database.Database;

public class NoSQLDatabase extends Database implements NoSQLInterface {
    @Override
    public Response disconnect() {
        return null;
    }

    @Override
    public Response deleteCollection() {
        return null;
    }

    @Override
    public Response connect() {
        return null;
    }
}
