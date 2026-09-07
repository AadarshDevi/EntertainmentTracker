package com.alphagnfss.etr3.database;

import com.alphagnfss.etr3.communication.Response;

public interface DatabaseInterface {
    Response connect();
    Response disconnect();
    Response get(int id);
    Response get(String text);
    Response post(int id);
    Response put(int id);
    Response patch(int id);
    Response delete(int id);
}
