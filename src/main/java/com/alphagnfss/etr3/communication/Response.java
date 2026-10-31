package com.alphagnfss.etr3.communication;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {

    ResponseCode responseCode; // Message (used for exceptions)
    Object object; // The object to pass

    public Response(ResponseCode responseCode, Object object) {
        this.responseCode = responseCode;
        this.object = object;
    }

    public Response(ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.object = null;
    }

    public static ResponseCode duplicate(ResponseCode responseCode) {
        return new ResponseCode(responseCode.code(), responseCode.message());
    }
}
