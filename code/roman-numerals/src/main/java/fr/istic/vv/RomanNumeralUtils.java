package fr.istic.vv;

import java.util.*;
import java.util.regex.Pattern;

public class RomanNumeralUtils {

    private static Map<String, Integer> ROMAN_CHARS_VALUES = new LinkedHashMap<>();
    private static Map<String, Integer> ROMAN_REPETITION_CONSTRAINTS = new HashMap<>();

    static {
        // List of available values of ROMAN chars
        ROMAN_CHARS_VALUES.put("M", 1000);
        ROMAN_CHARS_VALUES.put("CM", 900);
        ROMAN_CHARS_VALUES.put("D", 500);
        ROMAN_CHARS_VALUES.put("CD", 400);
        ROMAN_CHARS_VALUES.put("C", 100);
        ROMAN_CHARS_VALUES.put("XC", 90);
        ROMAN_CHARS_VALUES.put("L", 50);
        ROMAN_CHARS_VALUES.put("XL", 40);
        ROMAN_CHARS_VALUES.put("X", 10);
        ROMAN_CHARS_VALUES.put("IX", 9);
        ROMAN_CHARS_VALUES.put("V", 5);
        ROMAN_CHARS_VALUES.put("IV", 4);
        ROMAN_CHARS_VALUES.put("I", 1);

        // Symbols M, C, X, I can be repeated consecutively up to three times.
        ROMAN_REPETITION_CONSTRAINTS.put("M",3);
        ROMAN_REPETITION_CONSTRAINTS.put("C",3);
        ROMAN_REPETITION_CONSTRAINTS.put("X",3);
        ROMAN_REPETITION_CONSTRAINTS.put("I",3);
        // Symbols D, L, V can not be repeated.
        ROMAN_REPETITION_CONSTRAINTS.put("D",1);
        ROMAN_REPETITION_CONSTRAINTS.put("L",1);
        ROMAN_REPETITION_CONSTRAINTS.put("V",1);


    }

    // 2289 = MMCCLXXXIX = 1000+1000+100+100+50+10+10+10+

    public static List<String> getAllowedChars(){
        List<String> result = new ArrayList<>();
        ROMAN_CHARS_VALUES.forEach((key, value) -> {
            if(key.length() == 1){
                result.add(key);
            }
        });
        return result;
    }

    public static boolean isValidRomanNumeral(String value) {

        // Blank == 0
        if(value.isBlank()){
            return true;
        }

        List<String> valueAsList = List.of(value.split(""));
        // Check if all used chars are available in ROMAN
        if(!getAllowedChars().containsAll(valueAsList)){
            System.out.println("Invalid char used in '" + value + "', available chars : " + getAllowedChars());
            return false;
        }
        Pattern p = Pattern.compile("(M{1,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|M{0,3}(CM|C?D|D?C{1,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|M{0,3}(CM|CD|D?C{0,3})(XC|X?L|L?X{1,3})(IX|IV|V?I{0,3})|M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|I?V|V?I{1,3}))");
        return p.matcher(value).matches();
    }

    public static int parseRomanNumeral(String numeral) {
        if(numeral.isBlank()){
            return 0;
        }
        int total = 0;
        int current_value;
        for (int i = numeral.length()-1; i >= 0; i--) {
            current_value = ROMAN_CHARS_VALUES.get(String.valueOf(numeral.charAt(i)));
            if (4 * current_value < total){
                total -= current_value;
            }
            else{
                total += current_value;
            }
        }
        return total;
    }

    public static String toRomanNumeral(int number) {

        if(number>0 && number<4000){
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Integer> entry : ROMAN_CHARS_VALUES.entrySet()) {
                while (number >= entry.getValue()) {
                    number -= entry.getValue();
                    result.append(entry.getKey());
                }
            }
            return result.toString();
        }
        else{
            return "";
        }
    }
}
