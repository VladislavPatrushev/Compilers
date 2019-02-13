package com.example.myapplication;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTests {

    void assertCalcs(String input, int result) throws Exception {
        try (Lexer lexer = new Lexer(new StringReader(input))) {
            assertEquals(result, new Parser(lexer).executeCalculations());
        }
    }

    @Test
    void executeCalculationsSingleNumber() throws Exception {
        assertCalcs("12300", 12300);
    }

}
