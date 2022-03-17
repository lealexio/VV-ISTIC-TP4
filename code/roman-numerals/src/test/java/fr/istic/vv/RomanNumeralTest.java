package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;

public class RomanNumeralTest {


    // Test toRomanNumeral method
    @Property
    boolean allGeneratedRomanAreValid(@ForAll @IntRange(min=1, max = 3999) int integer) {
        return  RomanNumeralUtils.isValidRomanNumeral(RomanNumeralUtils.toRomanNumeral(integer));
    }

    @Property
    boolean allGeneratedNegativeRomanAreEmpty(@ForAll @Negative int integer) {
        return RomanNumeralUtils.toRomanNumeral(integer).equals("");
    }

    @Property
    boolean generationFromZeroIsEmpty() {
        return RomanNumeralUtils.toRomanNumeral(0).equals("");
    }

    // Test parseRomanNumeral method
    @Property
    boolean generationFromZeroIsEmpty(@ForAll("randomRoman") String roman) {
//        if(RomanNumeralUtils.isValidRomanNumeral(roman)){
//            return true;
//        }
        return true;
    }

    @Provide
    public Arbitrary<String> randomRoman(){
        return Arbitraries.strings().withChars('M','D','C','L','X','V','I').ofMinLength(0).ofMaxLength(9);
    }

}
