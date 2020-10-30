package com.ngkrychkov.piglatin.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.ngkrychkov.piglatin.utils.StringUtils.getStringWithoutNotLettersLowerCase;
import static com.ngkrychkov.piglatin.utils.StringUtils.isStringFirstCharConsonant;
import static com.ngkrychkov.piglatin.utils.StringUtils.isStringFirstCharVowel;
import static com.ngkrychkov.piglatin.utils.StringUtils.isWordContainsSuffix;
import static com.ngkrychkov.piglatin.utils.StringUtils.preserveApostropheAndEndingSymbols;
import static com.ngkrychkov.piglatin.utils.StringUtils.preserveCapitalLetters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {"qwer:qwer", "203hgnv:hgnv", "1@awer%:awer"}, delimiter = ':')
    void getStringWithoutNotLettersLowerCaseTest(String input, String expected) {
        String actualValue = getStringWithoutNotLettersLowerCase(input);
        assertEquals(expected, actualValue);
    }

    @Test
    void isStringFirstCharVowelTest() {
        assertTrue(isStringFirstCharVowel("own"));
        assertFalse(isStringFirstCharVowel("Hello"));
    }

    @Test
    void isStringFirstCharConsonantTest() {
        assertFalse(isStringFirstCharConsonant("own"));
        assertTrue(isStringFirstCharConsonant("Hello"));
    }

    @Test
    void isWordContainsSuffixTest() {
        String suffix = "way";
        assertTrue(isWordContainsSuffix("stairway", suffix));
        assertFalse(isWordContainsSuffix("stair", suffix));
        assertFalse(isWordContainsSuffix("st", suffix));
    }

    @ParameterizedTest
    @CsvSource(value = {"Qwer:rewq:Rewq", "USA:asuay:ASUay"}, delimiter = ':')
    void preserveCapitalLettersTest(String input, String transformed, String expected) {
        String actual = preserveCapitalLetters(input, transformed);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"test't:esttay:estta'y", "end.:enway:enway."}, delimiter = ':')
    void preserveApostropheAndEndingSymbolsTest(String input, String transformed, String expected) {
        String actual = preserveApostropheAndEndingSymbols(input, transformed);
        assertEquals(expected, actual);
    }
}
