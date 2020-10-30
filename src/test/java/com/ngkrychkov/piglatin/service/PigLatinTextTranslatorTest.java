package com.ngkrychkov.piglatin.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PigLatinTextTranslatorTest {

    @ParameterizedTest
    @CsvSource(value = {
            "Hello Mr. Black, I would like to talk about Stairway to Haven song, wouldn't you mind?" +
                    ":Ellohay Rmay. Lackbay, Iway ouldway ikelay otay alktay aboutway Stairway" +
                    " otay Avenhay ongsay, ouldntwa'y ouyay indmay?",
            "Hello:Ellohay",
            "McCloud:CcLoudmay",
            "hello:ellohay",
            "can't:antca'y",
            "wouldn't:ouldntwa'y",
            "song,:ongsay,",
            "apple:appleway",
            "USA:USAway",
            "John's:Ohnsja'y",
            "this-thing:histay-hingtay",
            "stairway:stairway",
            "end!:endway!",
            "End.:Endway.",
            "can't.:antca'y.",
            ".:."}, delimiter = ':')
    public void testTranslateStringToPigLatin(String input, String expected) {
        PigLatinTextTranslator st = new PigLatinTextTranslator();
        String actualValue = st.translateString(input);
        assertEquals(expected, actualValue);
    }

}
