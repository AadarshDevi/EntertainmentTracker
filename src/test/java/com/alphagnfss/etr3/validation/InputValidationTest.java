package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Responses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidationTest {
    private static final Logger logger = LogManager.getLogger(InputValidationTest.class);

    @Nested
    class StringTest {
        @Test
        public void nullString() {
            assertEquals(Responses.INPUT_NULL_ERROR, InputValidation.validString(null));
        }

        @Test
        public void emptyString() {
            assertEquals(Responses.INPUT_EMPTY_ERROR, InputValidation.validString(""));
        }

        @Test
        public void blankString() {
            assertEquals(Responses.INPUT_EMPTY_ERROR, InputValidation.validString("       "));
        }

        @Test
        public void validString() {
            assertEquals(Responses.SUCCESS, InputValidation.validString("EntertainmentTracker is the best app"));
        }

        @Test
        public void nullStringRegex() {
            assertEquals(Responses.SUCCESS, InputValidation.validString("EntertainmentTracker is the best app!", (String) null));
        }

        @Test
        public void nullEverything() {
            assertEquals(Responses.INPUT_NULL_ERROR, InputValidation.validString(null, (String) null));
        }

        @Test
        public void emptyStringNullRegex() {
            assertEquals(Responses.INPUT_EMPTY_ERROR, InputValidation.validString("", (String) null));
        }

        @Test
        public void nullPatternRegex() {
            assertEquals(Responses.SUCCESS, InputValidation.validString("EntertainmentTracker is the best app!", (Pattern) null));
        }
    }
}
