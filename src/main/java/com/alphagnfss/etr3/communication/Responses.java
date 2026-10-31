package com.alphagnfss.etr3.communication;

public class Responses {

    // general: 100 - 199
    public static final ResponseCode SUCCESS = new ResponseCode(100, "Success");

    // database: 200 - 299
    public static final ResponseCode DATABASE_ERROR = new ResponseCode(200, "Error in database");
    // // connection: 201 - 215
    public static final ResponseCode DATABASE_CONNECTION_ERROR = new ResponseCode(201, "Unable to connect to database");
    public static final ResponseCode DATABASE_DISCONNECTION_ERROR = new ResponseCode(202, "Unable to disconnect from database");
    // // crud
    // // connection: 216 - 225
    public static final ResponseCode DATABASE_DATA_CREATE_ERROR = new ResponseCode(216, "Unable to write data to database");
    public static final ResponseCode DATABASE_DATA_DELETE_ERROR = new ResponseCode(217, "Unable to delete data from database");
    public static final ResponseCode DATABASE_DATA_UPDATE_ERROR = new ResponseCode(218, "Unable to update data in database");
    public static final ResponseCode DATABASE_DATA_READ_ERROR = new ResponseCode(219, "Unable to read data from database");
    // frontend: 300 - 399
    public static final ResponseCode FRONTEND_ERROR = new ResponseCode(200, "Error in frontend");
    public static final ResponseCode FRONTEND_INPUT_EMPTY = new ResponseCode(201, "Field cannot be empty");
    // backend: 300 - 399

    // json parser
    public static final ResponseCode BACKEND_PARSER_ERROR = new ResponseCode(201, "Failed to parse data");

    public static boolean deepEquals(ResponseCode responseCode1, ResponseCode responseCode2) {
        return (
                responseCode1.code() == responseCode2.code()
                        && responseCode1.message().equals(responseCode2.message())
        );
    }
}
