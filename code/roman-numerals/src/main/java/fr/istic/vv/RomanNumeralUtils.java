package fr.istic.vv;

import java.util.*;
import java.util.regex.Pattern;

public class RomanNumeralUtils {

    private static List<String> ROMAN_CHARS = new ArrayList<>();
    private static Map<String, Integer> ROMAN_CHARS_VALUES = new HashMap<>();
    private static Map<String, Integer> ROMAN_REPETITION_CONSTRAINTS = new HashMap<>();

    static {

        // List of available chars of ROMAN
        ROMAN_CHARS.add("I");
        ROMAN_CHARS.add("V");
        ROMAN_CHARS.add("X");
        ROMAN_CHARS.add("L");
        ROMAN_CHARS.add("C");
        ROMAN_CHARS.add("D");
        ROMAN_CHARS.add("M");


        // List of available values of ROMAN chars
        ROMAN_CHARS_VALUES.put("I",1);
        ROMAN_CHARS_VALUES.put("V",5);
        ROMAN_CHARS_VALUES.put("X",10);
        ROMAN_CHARS_VALUES.put("L",50);
        ROMAN_CHARS_VALUES.put("C",100);
        ROMAN_CHARS_VALUES.put("D",500);
        ROMAN_CHARS_VALUES.put("M",1000);

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

    public static boolean isValidRomanNumeral(String value) {
//
//        // Blank == 0
//        if(value.isBlank()){
//            return true;
//        }
//
//        List<String> valueAsList = List.of(value.split(""));
//
//        // Check if all used chars are available in ROMAN
//        if(!ROMAN_CHARS.containsAll(valueAsList)){
//            System.out.println("Invalid char used in '" + value + "', available chars : " + ROMAN_CHARS.toString());
//            return false;
//        }
//
//        // Check if all chars repetitions are valid
//        for (Map.Entry<String, Integer> entry : ROMAN_REPETITION_CONSTRAINTS.entrySet()) {
//            if(value.contains(entry.getKey().repeat(entry.getValue() + 1))){
//                System.out.println("Error, char '" + entry.getKey() + "' repeated more than " + entry.getValue().toString() + " times");
//                return false;
//            }
//        }
//        return true;


        Pattern p = Pattern.compile("(M{1,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|M{0,4}(CM|C?D|D?C{1,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|M{0,4}(CM|CD|D?C{0,3})(XC|X?L|L?X{1,3})(IX|IV|V?I{0,3})|M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|I?V|V?I{1,3}))");
        System.out.println(p.matcher(value));
        return p.matcher(value).matches();
    }

    public static int parseRomanNumeral(String numeral) {
        int total = 0;
        int current_value = 0;
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

    public static String toRomanNumeral(int number) { return ""; }

}
