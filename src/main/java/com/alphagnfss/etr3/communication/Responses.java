package com.alphagnfss.etr3.communication;

public class Responses {

    // database: 100 - 199
    public static final ResponseCode DATABASE_ERROR = new ResponseCode(100, "Error in database");
    public static final ResponseCode DATABASE_CONNECTION_ERROR = new ResponseCode(100, "Unable to connect to database");
    public static final ResponseCode DATABASE_CONNECTION_SUCCESSFUL = new ResponseCode(100, "Successfully connected to database");
    public static final ResponseCode DATABASE_DISCONNECTION_ERROR = new ResponseCode(100, "Unable to disconnect from database");
    public static final ResponseCode DATABASE_DISCONNECTION_SUCCESSFUL = new ResponseCode(100, "Successfully disconnected from database");
    // // crud
    public static final ResponseCode DATABASE_DATA_CREATE_ERROR = new ResponseCode(100, "Unable to write data to database");
    public static final ResponseCode DATABASE_DATA_CREATE_SUCCESSFUL = new ResponseCode(100, "Successfully wrote data to database");
    public static final ResponseCode DATABASE_DATA_DELETE_ERROR = new ResponseCode(100, "Unable to delete data from database");
    public static final ResponseCode DATABASE_DATA_DELETE_SUCCESSFUL = new ResponseCode(100, "Successfully deleted data from database");
    public static final ResponseCode DATABASE_DATA_UPDATE_ERROR = new ResponseCode(100, "Unable to update data in database");
    public static final ResponseCode DATABASE_DATA_UPDATE_SUCCESSFUL = new ResponseCode(100, "Successfully updated data in database");
    public static final ResponseCode DATABASE_DATA_READ_ERROR = new ResponseCode(100, "Unable to read data from database");
    public static final ResponseCode DATABASE_DATA_READ_SUCCESSFUL = new ResponseCode(100, "Successfully read data from database");
    // frontend: 200 - 299
    public static final ResponseCode FRONTEND_ERROR = new ResponseCode(200, "Error in frontend");
    public static final ResponseCode FRONTEND_INPUT_EMPTY = new ResponseCode(201, "Field cannot be empty");
    // backend: 300 - 399


    public static boolean equals(ResponseCode responseCode1, ResponseCode responseCode2) {
        return (
                responseCode1.code() == responseCode2.code()
                        && responseCode1.message().equals(responseCode2.message())
        );
    }
}
