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

    public Response(int code) {
        this(code, null, null);
    }

    public Response(int code, String message) {
        this(code, message, null);
    }

    public Response(int code, Object object) {
        this(code, null, object);
    }

    public Response notImplemented(String methodHeader) {
        return new Response(501, "Method not been implemented yet: " + methodHeader + "()", null);
    }
}
