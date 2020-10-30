package com.ngkrychkov.piglatin.utils;

import static com.ngkrychkov.piglatin.utils.Constants.APOSTROPHE;
import static com.ngkrychkov.piglatin.utils.Constants.PUNCTUATION_MARKS;

public class StringUtils {

    private StringUtils() {
        /*un-instantiable*/
    }

    public static String getStringWithoutNotLettersLowerCase(String inputStr) {
        return inputStr.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    public static boolean isStringFirstCharVowel(String inputStr) {
        return "aeiou".indexOf(Character.toLowerCase(inputStr.charAt(0))) != -1;
    }

    public static boolean isStringFirstCharConsonant(String inputStr) {
        return "bcdfghjklmnpqrstvwxyz".indexOf(Character.toLowerCase(inputStr.charAt(0))) != -1;
    }

    public static boolean isWordContainsSuffix(String inputStr, String suffix) {
        if (inputStr.length() < suffix.length()) {
            return false;
        }
        return inputStr.endsWith(suffix);
    }

    public static String moveFirstCharToEndAndAppendSuffix(String inputStr, String suffix) {
        return appendSuffix(inputStr.substring(1) +
                inputStr.substring(0, 1), suffix);
    }

    public static String appendSuffix(String inputStr, String suffix) {
        return inputStr + suffix;
    }

    public static String preserveCapitalLetters(String original, String transformed) {
        StringBuilder sb = new StringBuilder(transformed);

        for (int i = 0; i < original.length(); i++) {
            if (Character.isUpperCase(original.charAt(i))) {
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            }
        }
        return sb.toString();
    }

    // We assume that the apostrophe is just one in grammar sense.
    public static String preserveApostropheAndEndingSymbols(String original, String transformed) {
        StringBuilder transformedSb = new StringBuilder(transformed);

        if (original.contains(APOSTROPHE)) {
            transformedSb.insert(transformed.length() - 1, APOSTROPHE);
        }
        int lastCharIndex = original.length() - 1;
        if (PUNCTUATION_MARKS.contains(original.substring(lastCharIndex))) {
            transformedSb.append(original.charAt(lastCharIndex));
        }
        return transformedSb.toString();
    }
}
