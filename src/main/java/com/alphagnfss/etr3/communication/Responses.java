package com.alphagnfss.etr3.communication;

public class Responses {

    // database: 100 - 199
    public static final ResponseCode DATABASE_ERROR = new ResponseCode(100, "Error in database");
    // frontend: 200 - 299
    public static final ResponseCode FRONTEND_ERROR = new ResponseCode(200, "Error in frontend");
    public static final ResponseCode FRONTEND_INPUT_EMPTY = new ResponseCode(201, "Field cannot be empty");
    // backend: 300 - 399

}
