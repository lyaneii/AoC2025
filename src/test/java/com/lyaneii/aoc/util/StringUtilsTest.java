package com.lyaneii.aoc.util;

import com.lyaneii.aoc.common.util.StringUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {
    @Nested
    public class FindFirstIntegerTest {
        @Test
        void shouldCorrectlyParseNumberOnly() {
            String input = "123456789";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(123456789, result);
        }
        @Test
        void shouldCorrectlyParseLettersFollowedByNumbers() {
            String input = "test123";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(123, result);
        }
        @Test
        void shouldCorrectlyParseNumbersFollowedByLetters() {
            String input = "123test";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(123, result);
        }
        @Test
        void shouldCorrectlyParseMultipleNumbers() {
            String input = "test321test123test456";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(321, result);
        }
        @Test
        void shouldCorrectlyParseNegativeNumber() {
            String input = "-123";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(-123, result);
        }
        @Test
        void shouldCorrectlyParseTwoNegativeNumbers() {
            String input = "-123-456";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(-123, result);
        }
        @Test
        void shouldCorrectlyParseNumberWithDoubleSign() {
            String input = "--123";
            int result = StringUtils.findFirstInteger(input);
            assertEquals(-123, result);
        }
        @Test
        void shouldThrowWhenNoNumberIsPresent() {
            String input = "abcdefgh";
            assertThrows(RuntimeException.class,
                    () -> StringUtils.findFirstInteger(input));
        }
        @Test
        void shouldThrowWhenNumberIsTooLarge() {
            String input = "123456789123456789123456789";
            assertThrows(RuntimeException.class,
                    () -> StringUtils.findFirstInteger(input));
        }
    }

    @Nested
    public class FindFirstLongTest {
        @Test
        void shouldCorrectlyParseNumberOnly() {
            String input = "1234567890123457";
            long result = StringUtils.findFirstLong(input);
            assertEquals(1234567890123457L, result);
        }
        @Test
        void shouldCorrectlyParseLettersFollowedByNumbers() {
            String input = "test1234567890123457";
            long result = StringUtils.findFirstLong(input);
            assertEquals(1234567890123457L, result);
        }
        @Test
        void shouldCorrectlyParseNumbersFollowedByLetters() {
            String input = "1234567890123457test";
            long result = StringUtils.findFirstLong(input);
            assertEquals(1234567890123457L, result);
        }
        @Test
        void shouldCorrectlyParseMultipleNumbers() {
            String input = "test1234567890123457test123test456";
            long result = StringUtils.findFirstLong(input);
            assertEquals(1234567890123457L, result);
        }
        @Test
        void shouldCorrectlyParseNegativeNumber() {
            String input = "-1234567890123457";
            long result = StringUtils.findFirstLong(input);
            assertEquals(-1234567890123457L, result);
        }
        @Test
        void shouldCorrectlyParseTwoNegativeNumbers() {
            String input = "-1234567890123457-456";
            long result = StringUtils.findFirstLong(input);
            assertEquals(-1234567890123457L, result);
        }
        @Test
        void shouldCorrectlyParseNumberWithDoubleSign() {
            String input = "--1234567890123457";
            long result = StringUtils.findFirstLong(input);
            assertEquals(-1234567890123457L, result);
        }
        @Test
        void shouldThrowWhenNoNumberIsPresent() {
            String input = "abcdefgh";
            assertThrows(RuntimeException.class,
                    () -> StringUtils.findFirstLong(input));
        }
        @Test
        void shouldThrowWhenNumberIsTooLarge() {
            String input = "1234567891234567891234567891354765374367";
            assertThrows(RuntimeException.class,
                    () -> StringUtils.findFirstLong(input));
        }
    }

    @Nested
    public class FindFirstFloatTest {
        @Test
        void shouldCorrectlyParseNumberOnly() {
            String input = "123.456";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(123.456f, result);
        }
        @Test
        void shouldCorrectlyParseLettersFollowedByNumbers() {
            String input = "test123.456";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(123.456f, result);
        }
        @Test
        void shouldCorrectlyParseNumbersFollowedByLetters() {
            String input = "123.456test";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(123.456f, result);
        }
        @Test
        void shouldCorrectlyParseMultipleNumbers() {
            String input = "test321.654test123test456";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(321.654f, result);
        }
        @Test
        void shouldCorrectlyParseNegativeNumber() {
            String input = "-123.456";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(-123.456f, result);
        }
        @Test
        void shouldCorrectlyParseTwoNegativeNumbers() {
            String input = "-123.456-456.789";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(-123.456f, result);
        }
        @Test
        void shouldCorrectlyParseNumberWithDoubleSign() {
            String input = "--123.456";
            float result = StringUtils.findFirstFloat(input);
            assertEquals(-123.456f, result);
        }
        @Test
        void shouldThrowWhenNoNumberIsPresent() {
            String input = "abcdefgh";
            assertThrows(RuntimeException.class,
                    () -> StringUtils.findFirstFloat(input));
        }
    }

    @Nested
    public class FindFirstDoubleTest {
        @Test
        void shouldCorrectlyParseNumberOnly() {
            String input = "123.456";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(123.456, result);
        }
        @Test
        void shouldCorrectlyParseLettersFollowedByNumbers() {
            String input = "test123.456";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(123.456, result);
        }
        @Test
        void shouldCorrectlyParseNumbersFollowedByLetters() {
            String input = "123.456test";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(123.456, result);
        }
        @Test
        void shouldCorrectlyParseMultipleNumbers() {
            String input = "test321.654test123test456";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(321.654, result);
        }
        @Test
        void shouldCorrectlyParseNegativeNumber() {
            String input = "-123.456";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(-123.456, result);
        }
        @Test
        void shouldCorrectlyParseTwoNegativeNumbers() {
            String input = "-123.456-456.789";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(-123.456, result);
        }
        @Test
        void shouldCorrectlyParseNumberWithDoubleSign() {
            String input = "--123.456";
            double result = StringUtils.findFirstDouble(input);
            assertEquals(-123.456, result);
        }
        @Test
        void shouldThrowWhenNoNumberIsPresent() {
            String input = "abcdefgh";
            assertThrows(RuntimeException.class,
                    () -> StringUtils.findFirstDouble(input));
        }
    }
}
