package com.alphagnfss.etr3.communication;

import lombok.Getter;

public class Response {

    @Getter int code;
    @Getter String message;
    @Getter Object object;

    public Response(int code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
}
