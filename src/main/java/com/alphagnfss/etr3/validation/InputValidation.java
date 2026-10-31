package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.communication.Responses;

import java.util.regex.Pattern;

public class InputValidation {
    public static Response validString(String string) {
        Response response = Response.builder().code().message().object().build();

    /**
     * The method takes in a string and uses a regex containing invalid chars to check if the string has any of those.
     *
     * @param string the input string
     * @param regex  list of chars that are banned in the string
     * @return string is a valid input
     */
    public static Response validString(String string, String regex) {

        if (string == null) return Response.builder().responseCode(Responses.INPUT_NULL_ERROR).build();
        else if (string.isBlank()) return Response.builder().responseCode(Responses.INPUT_EMPTY_ERROR).build();

        if (regex != null && !regex.isBlank()) {
            boolean hasInvalidChars = Pattern.compile(regex).matcher(string).find();
            if (hasInvalidChars) return Response.builder().responseCode(Responses.INPUT_INVALID_CHARACTERS).build();
        }
        return Response.builder().responseCode(Responses.SUCCESS).build();
    }
}
