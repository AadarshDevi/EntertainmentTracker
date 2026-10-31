package com.alphagnfss.etr3.communication;

public class Responses {

    // database: 100 - 199
    public static final ResponseCode DATABASE_ERROR = new ResponseCode(100, "Error in database");
    public static final ResponseCode DATABASE_CONNECTION_ERROR = new ResponseCode(100, "Unable to connect to database");
    public static final ResponseCode DATABASE_CONNECTION_SUCCESSFUL = new ResponseCode(100, "Successfully connected to database");
    public static final ResponseCode DATABASE_DISCONNECTION_ERROR = new ResponseCode(100, "Unable to disconnect from database");
    public static final ResponseCode DATABASE_DISCONNECTION_SUCCESSFUL = new ResponseCode(100, "Successfully disconnected from database");
    // frontend: 200 - 299
    public static final ResponseCode FRONTEND_ERROR = new ResponseCode(200, "Error in frontend");
    public static final ResponseCode FRONTEND_INPUT_EMPTY = new ResponseCode(201, "Field cannot be empty");
    // backend: 300 - 399


    public boolean equals(ResponseCode responseCode1, ResponseCode responseCode2) {
        return (
                responseCode1.code() == responseCode2.code()
                        && responseCode1.message().equals(responseCode2.message())
        );
    }
}
