package com.alphagnfss.etr3.communication;

public class Responses {

    // general: 100 - 199
    public static final ResponseCode SUCCESS = new ResponseCode(100, "Success");
    public static final ResponseCode NO_ACCESS = new ResponseCode(101, "User cannot access this part of the app.");

    // database: 200 - 299
    public static final ResponseCode DATABASE_ERROR = new ResponseCode(200, "Error in database");
    // // connection: 201 - 215
    public static final ResponseCode DATABASE_CONNECTION_ERROR = new ResponseCode(201, "Unable to connect to database");
    public static final ResponseCode DATABASE_DISCONNECTION_ERROR = new ResponseCode(202, "Unable to disconnect from database");
    public static final ResponseCode DATABASE_STORAGE_CONTAINER_CREATION_ERROR = new ResponseCode(203, "Unable to create the storage container of the database");
    public static final ResponseCode DATABASE_STORAGE_CONTAINER_DELETION_ERROR = new ResponseCode(204, "Unable to delete the storage container of the database");

    // // crud ops: 216 - 225
    public static final ResponseCode DATABASE_DATA_CREATE_ERROR = new ResponseCode(216, "Unable to write data to database");
    public static final ResponseCode DATABASE_DATA_DELETE_ERROR = new ResponseCode(217, "Unable to delete data from database");
    public static final ResponseCode DATABASE_DATA_UPDATE_ERROR = new ResponseCode(218, "Unable to update data in database");
    public static final ResponseCode DATABASE_DATA_READ_ERROR = new ResponseCode(219, "Unable to read data from database");
    // frontend: 300 - 399
    public static final ResponseCode FRONTEND_ERROR = new ResponseCode(300, "Error in frontend");
    public static final ResponseCode FRONTEND_INPUT_EMPTY = new ResponseCode(301, "Field cannot be empty");
    // backend: 400 - 499

    // input validation
    public static final ResponseCode INPUT_ERROR = new ResponseCode(411, "Input error");
    public static final ResponseCode INPUT_NULL_ERROR = new ResponseCode(412, "Input cannot be null");
    public static final ResponseCode INPUT_EMPTY_ERROR = new ResponseCode(413, "Input cannot be empty");
    public static final ResponseCode INPUT_INVALID_CHARACTERS = new ResponseCode(414, "Input has invalid characters");

    // json parser
    public static final ResponseCode BACKEND_PARSER_ERROR = new ResponseCode(431, "Failed to parse data");

    public static boolean deepEquals(ResponseCode responseCode1, ResponseCode responseCode2) {

        if (responseCode1 == null || responseCode2 == null) {
            return false;
        }

        return (
                responseCode1.code() == responseCode2.code()
                        && responseCode1.message().equals(responseCode2.message())
        );
    }
}
