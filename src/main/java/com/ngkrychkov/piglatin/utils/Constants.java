package com.ngkrychkov.piglatin.utils;

import java.util.Set;

public final class Constants {

    public static final String SPACE = " ";
    public static final String HYPHEN = "-";
    public static final String APOSTROPHE = "'";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String QUESTION_MARK = "?";
    public static final String EXCLAMATION_MARK = "!";
    public static final String COLON = ":";
    public static final String SEMI_COLON = ";";

    public static final String WAY = "way";
    public static final String AY = "ay";

    public static Set<String> PUNCTUATION_MARKS = Set.of(DOT, COMMA, QUESTION_MARK, EXCLAMATION_MARK, COLON, SEMI_COLON);

    private Constants() {
        /*un-instantiable*/
    }
}
