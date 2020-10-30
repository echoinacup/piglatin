package com.ngkrychkov.piglatin.service;


import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static com.ngkrychkov.piglatin.utils.Constants.AY;
import static com.ngkrychkov.piglatin.utils.Constants.HYPHEN;
import static com.ngkrychkov.piglatin.utils.Constants.SPACE;
import static com.ngkrychkov.piglatin.utils.Constants.WAY;
import static com.ngkrychkov.piglatin.utils.StringUtils.appendSuffix;
import static com.ngkrychkov.piglatin.utils.StringUtils.getStringWithoutNotLettersLowerCase;
import static com.ngkrychkov.piglatin.utils.StringUtils.isStringFirstCharConsonant;
import static com.ngkrychkov.piglatin.utils.StringUtils.isStringFirstCharVowel;
import static com.ngkrychkov.piglatin.utils.StringUtils.isWordContainsSuffix;
import static com.ngkrychkov.piglatin.utils.StringUtils.moveFirstCharToEndAndAppendSuffix;
import static com.ngkrychkov.piglatin.utils.StringUtils.preserveApostropheAndEndingSymbols;
import static com.ngkrychkov.piglatin.utils.StringUtils.preserveCapitalLetters;


public class PigLatinTextTranslator implements TextTranslator {

    public String translateString(String inputString) {
        String trailed = inputString.trim();
        if (trailed.contains(SPACE)) {
            return separateAndProcessStringBasedOnDelimiter(trailed, SPACE);
        }
        return processSingleString(trailed);
    }

    /**
     * Returns original converted to string with PigLatin rules applied.
     * original word is filtered out of none alphabetic characters and flatted to lower case
     * for the easy of the following rules been applied
     * @param originalWord
     * @return string in PigLatin encoding or original word
     * if original word has suffix "way" or string after flattening is empty.
     */
    private String applyTranslationRules(String originalWord) {
        String stringWithoutNoneLetters = getStringWithoutNotLettersLowerCase(originalWord);
        if (stringWithoutNoneLetters.length() == 0) {
            return originalWord;
        }
        if (!isWordContainsSuffix(stringWithoutNoneLetters, WAY)) {
            if (isStringFirstCharVowel(stringWithoutNoneLetters)) {
                String transformed = appendSuffix(stringWithoutNoneLetters, WAY);
                transformed = preserveCapitalLetters(originalWord, transformed);
                return preserveApostropheAndEndingSymbols(originalWord, transformed);
            }
            if (isStringFirstCharConsonant(stringWithoutNoneLetters)) {
                String transformed = moveFirstCharToEndAndAppendSuffix(stringWithoutNoneLetters, AY);
                transformed = preserveCapitalLetters(originalWord, transformed);
                return preserveApostropheAndEndingSymbols(originalWord, transformed);
            }
        }
        return originalWord;
    }

    private String processSingleString(String input) {
        if (input.contains(HYPHEN)) {
            return separateAndProcessStringBasedOnDelimiter(input, HYPHEN);
        }
        return applyTranslationRules(input);
    }

    private String separateAndProcessStringBasedOnDelimiter(String inputStr, String delimiter) {
        List<String> separateStrings = Arrays.asList(inputStr.split(delimiter));
        StringJoiner sj = new StringJoiner(delimiter);
        separateStrings.forEach(s -> sj.add(processSingleString(s)));
        return sj.toString();
    }
}
