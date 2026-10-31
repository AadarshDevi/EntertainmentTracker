package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Response;

public class InputValidation {
    public static Response validString(String string) {
        Response response = Response.builder().code().message().object().build();

        if (string == null) return reponse;

        if (string == null) return Response.builder().responseCode(Responses.INPUT_NULL_ERROR).build();
        else if (string.isBlank()) return Response.builder().responseCode(Responses.INPUT_EMPTY_ERROR).build();

        if (regex != null && !regex.isBlank()) {
            boolean hasInvalidChars = Pattern.compile(regex).matcher(string).find();
            if (hasInvalidChars) return Response.builder().responseCode(Responses.INPUT_INVALID_CHARACTERS).build();
        }
        return Response.builder().responseCode(Responses.SUCCESS).build();
    }
}
