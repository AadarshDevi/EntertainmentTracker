package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Response;

public class InputValidation {
    public static Response validString(String string) {
        Response response = Response.builder().code().message().object().build();

        if (string == null) return reponse;

    }
}
