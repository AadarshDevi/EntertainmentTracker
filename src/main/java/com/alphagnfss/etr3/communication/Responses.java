package com.alphagnfss.etr3.communication;

public class Responses {

    // database: 100 - 199
    public static final ResponseCode DATABASE_ERROR = new ResponseCode(100, "Error in database");
    // frontend: 200 - 299
    public static final ResponseCode FRONTEND_INPUT_EMPTY = new ResponseCode(100, "Field cannot be empty");
    public static final ResponseCode FRONTEND_ERROR = new ResponseCode(100, "Error in frontend");
//    public static final ResponseCode FRONTEND_INPUT_INVALID = new ResponseCode(100, "The input is invalid");
    // backend: 300 - 399
    
}
