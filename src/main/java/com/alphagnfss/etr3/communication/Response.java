package com.alphagnfss.etr3.communication;

import lombok.Builder;
import lombok.Getter;

@Builder
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
