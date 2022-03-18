package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

public class RomanNumeralTest {


    // Test toRomanNumeral : All generated roman in [1,3999] are valid
    @Property
    boolean allGeneratedRomanAreValid(@ForAll @IntRange(min=1, max = 3999) int integer) {
        return  RomanNumeralUtils.isValidRomanNumeral(RomanNumeralUtils.toRomanNumeral(integer));
    }

    // Test toRomanNumeral : All generated roman in ]-∞,0[ are not valid
    @Property
    boolean allGeneratedNegativeRomanAreEmpty(@ForAll @Negative int integer) {
        return RomanNumeralUtils.toRomanNumeral(integer).equals("");
    }

    // Test toRomanNumeral : All generated roman in [4000, +∞[ are not valid
    @Property
    boolean allGeneratedRomanOverAreEmpty(@ForAll @IntRange(min=4000) int integer) {
        return RomanNumeralUtils.toRomanNumeral(integer).equals("");
    }

    // Test toRomanNumeral : Roman from value 0 is not valid
    @Property
    boolean generationFromZeroIsEmpty() {
        return RomanNumeralUtils.toRomanNumeral(0).equals("");
    }


    // Test parseRomanNumeral : parse random numeral
    @Property
    boolean generateRandomRoman(@ForAll("randomRoman") String roman) {
        if(RomanNumeralUtils.isValidRomanNumeral(roman)){
            System.out.println("---"+roman);
            int value = RomanNumeralUtils.parseRomanNumeral(roman);
            return (value>0 && value<4000);
        }
        return true;
    }

    // Test parseRomanNumeral : parse empty numeral
    @Property
    boolean generateRandomRoman() {
        return RomanNumeralUtils.parseRomanNumeral("") == 0;
    }

    @Provide
    public Arbitrary<String> randomRoman(){
        return Arbitraries.strings().withChars('M','D','C','L','X','V','I').ofMinLength(1).ofMaxLength(9);
    }

}
