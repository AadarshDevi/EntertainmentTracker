package com.alphagnfss.etr3.database.nosql;

import com.alphagnfss.etr3.communication.Response;

public interface NoSQLInterface {
    Response disconnect();

    Response deleteCollection();

    Response connect();
}
