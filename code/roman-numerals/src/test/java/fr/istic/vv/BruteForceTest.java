package fr.istic.vv;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class BruteForceTest {

    private final Map<String, Integer> valueByRoman = new HashMap<>();


    @Before
    public void setUp() {
        try {
            File file = new File("src/test/resources/allRoman.csv");
            List<String> allLines = Files.readAllLines(file.toPath());
            for (String line : allLines) {
                String[] lineAsArray = line.split(",");
                if(lineAsArray.length == 2){
                    valueByRoman.put(lineAsArray[1], Integer.parseInt(lineAsArray[0]));
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseRomanNumeral(){
        for (Map.Entry<String, Integer> entry : valueByRoman.entrySet()) {
            int value = RomanNumeralUtils.parseRomanNumeral(entry.getKey());
            int expected = entry.getValue();
            assertEquals(expected, value);
        }
    }

    @Test
    public void testGenerateRomanNumeral(){
        for (Map.Entry<String, Integer> entry : valueByRoman.entrySet()) {
            String value = RomanNumeralUtils.toRomanNumeral(entry.getValue());
            String expected = entry.getKey();
            assertEquals(expected, value);
        }
    }

}
