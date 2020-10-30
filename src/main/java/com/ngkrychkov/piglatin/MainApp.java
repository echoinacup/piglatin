package com.ngkrychkov.piglatin;

import com.ngkrychkov.piglatin.service.PigLatinTextTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    /**
     * Input constraint ASCII only characters
     */
    public static void main(String[] args) throws IOException {
        PigLatinTextTranslator translator = new PigLatinTextTranslator();
        String fileName = "input/text.txt";
        ClassLoader classLoader = MainApp.class.getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        LOG.info("Original Content of the file");
        LOG.info(content);
        String toTranslate = content;

        if (!content.matches("\\A\\p{ASCII}*\\z")) {
            LOG.warn("Input text contains non ASCII char we will filter them out.");
            toTranslate = filterInputTextFromNonAsciiChars(content);
        }

        LOG.info("Content of the file after encoding");
        LOG.info(translator.translateString(toTranslate));
    }

    private static String filterInputTextFromNonAsciiChars(String originalText) {
        String filteredString = originalText.replaceAll("[^\\p{ASCII}]", "");
        LOG.warn("Filtered text without non ASCII characters");
        LOG.warn(filteredString);
        return filteredString;
    }
}
