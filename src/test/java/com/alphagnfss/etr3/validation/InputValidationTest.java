package com.alphagnfss.etr3.validation;

import com.alphagnfss.etr3.communication.Responses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidationTest {
    private static final Logger logger = LogManager.getLogger(InputValidationTest.class);

    @Nested
    class StringTest {
        @Test
        public void nullString() {
            assertEquals(InputValidation.validString(null).getResponseCode().code(), Responses.INPUT_NULL_ERROR);
        }

        @Test
        public void emptyString() {
            assertEquals(InputValidation.validString("").getResponseCode().code(), Responses.INPUT_EMPTY_ERROR);
        }

        @Test
        public void blankString() {
            assertEquals(InputValidation.validString("       ").getResponseCode().code(), Responses.INPUT_EMPTY_ERROR);
        }

        @Test
        public void validString() {
            assertEquals(InputValidation.validString("EntertainmentTracker is the best app").getResponseCode().code(), Responses.INPUT_EMPTY_ERROR);
        }

        @Test
        public void nullStringRegex() {
            assertEquals(InputValidation.validString("EntertainmentTracker is the best app!", (String) null).getResponseCode().code(), Responses.INPUT_EMPTY_ERROR);
        }

        @Test
        public void nullEverything() {
            assertEquals(InputValidation.validString(null, (String) null).getResponseCode().code(), Responses.INPUT_EMPTY_ERROR);
        }

        @Test
        public void nullPatternRegex() {
            assertEquals(InputValidation.validString("EntertainmentTracker is the best app!", (Pattern) null).getResponseCode().code(), Responses.INPUT_EMPTY_ERROR);
        }
    }
}
