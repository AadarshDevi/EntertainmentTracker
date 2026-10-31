package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.ResponseCode;
import com.alphagnfss.etr3.communication.Responses;
import com.alphagnfss.etr3.data.enums.EntertainmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class InputValidation {
    private static final Logger logger = LogManager.getLogger(InputValidation.class);

    /**
     * Checking the string to see if it is empty, null or has invalid chars given by the regex
     *
     * @param inputString    the input string
     * @param forbiddenRegex list of chars that are banned in the string
     * @return string is a valid input
     */
    public static ResponseCode validString(String inputString, String forbiddenRegex) {
        if (forbiddenRegex == null || forbiddenRegex.isBlank())
            return validString(inputString, (Pattern) null);

        Pattern pattern = Pattern.compile(forbiddenRegex);
        return validString(inputString, pattern);
    }

    /**
     * Checking the string to see if it is empty, null or has invalid chars given by the pattern
     *
     * @param inputString input to be validated
     * @param pattern     pattern regex used to validate input
     * @return response if the validation was successful or not
     */
    public static ResponseCode validString(String inputString, Pattern pattern) {
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
        return responseCode;
    }

    public static ResponseCode validString(String inputString) {
        return validString(inputString, (Pattern) null);
    }

    public static ResponseCode validType(String type) {

        ResponseCode responseCode = validString(type);

        if (responseCode != Responses.SUCCESS) {
            return responseCode;
        }

        try {
            EntertainmentType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return Responses.INPUT_INVALID_DATA_TYPE;
        }
        return Responses.SUCCESS;
    }
}
