package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.communication.Responses;

import java.util.regex.Pattern;

public class InputValidation {

    /**
     * The method takes in a string and uses a regex containing invalid chars to check if the string has any of those.
     *
     * @param inputString    the input string
     * @param forbiddenRegex list of chars that are banned in the string
     * @return string is a valid input
     */
    public static Response validString(String inputString, String forbiddenRegex) {
        Pattern pattern = Pattern.compile(forbiddenRegex);
        return validString(inputString, pattern);
    }

    public static Response validString(String inputString, Pattern pattern) {
        if (inputString == null) return Response.builder().responseCode(Responses.INPUT_NULL_ERROR).build();
        else if (inputString.isBlank()) return Response.builder().responseCode(Responses.INPUT_EMPTY_ERROR).build();

        if (pattern != null && pattern.matcher(inputString).find())
            return Response.builder().responseCode(Responses.INPUT_INVALID_CHARACTERS).build();

        return Response.builder().responseCode(Responses.SUCCESS).build();
    }

    public static Response validString(String inputString) {
        return validString(inputString, "");
    }
}
