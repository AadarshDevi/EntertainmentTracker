package com.alphagnfss.etr3.communication;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Response {

    @Getter int code; // HTTP Code
    @Getter String message; // Message (used for exceptions)
    @Getter Object object; // The object to pass

    public Response(int code, String message, Object object) {
        this.code = code;
        this.message = message;
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
