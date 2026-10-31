package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.communication.ResponseCode;
import com.alphagnfss.etr3.communication.Responses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class InputValidation {
    private static final Logger logger = LogManager.getLogger(InputValidation.class);

    /**
     * The method takes in a string and uses a regex containing invalid chars to check if the string has any of those.
     *
     * @param inputString    the input string
     * @param forbiddenRegex list of chars that are banned in the string
     * @return string is a valid input
     */
    public static Response validString(String inputString, String forbiddenRegex) {
        if (forbiddenRegex == null || forbiddenRegex.isBlank())
            return validString(inputString, (Pattern) null);

        Pattern pattern = Pattern.compile(forbiddenRegex);
        return validString(inputString, pattern);
    }

    public static Response validString(String inputString, Pattern pattern) {
        ResponseCode responseCode;

        if (inputString == null)
            responseCode = Responses.INPUT_NULL_ERROR;
        else if (inputString.isBlank())
            responseCode = Responses.INPUT_EMPTY_ERROR;
        else if (pattern != null && pattern.matcher(inputString).find())
            responseCode = Responses.INPUT_INVALID_CHARACTERS;
        else
            responseCode = Responses.SUCCESS;

        logger.debug("String Validation: {}", responseCode);
        return Response.builder().responseCode(Responses.SUCCESS).build();
    }

    public static Response validString(String inputString) {
        return validString(inputString, (String) null);
    }
}
