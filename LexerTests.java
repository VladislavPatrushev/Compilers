package com.example.myapplication;


import org.junit.jupiter.api.Test;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.example.myapplication.Type.*;

class LexerTest {

    private void assertLexemeTypes(String input, Type... types) throws Exception {
        try (Lexer lexer = new Lexer(new StringReader(input))) {
            for (Type type : types)
                assertEquals(type, lexer.LexemeGet().getType());
        }
    }

    private void assertLexemeValues(String input, String... values) throws Exception {
        try (Lexer lexer = new Lexer(new StringReader(input))) {
            for (String value : values)
                assertEquals(value, lexer.LexemeGet().getText());
        }
    }

    @Test
    void nextLexemeSimpleSingleTypes() throws Exception {
        assertLexemeTypes("", EOF);
        assertLexemeTypes(" ", EOF);
        assertLexemeTypes("    ", EOF);
        assertLexemeTypes("\t", EOF);
        assertLexemeTypes("\t\t\t\t", EOF);
        assertLexemeTypes("\t \t  \t   \t    ", EOF);
        assertLexemeTypes("(", BRACKET_OPEN, EOF);
        assertLexemeTypes(")", BRACKET_CLOSE, EOF);
        assertLexemeTypes("+", PLUS, EOF);
        assertLexemeTypes("-", MINUS, EOF);
        assertLexemeTypes("*", MULTIPLY, EOF);
        assertLexemeTypes("/", DIVIDE, EOF);
        assertLexemeTypes("^", POWER, EOF);
    }

    @Test
    void nextLexemeSimpleNumberTypes() throws Exception {
        assertLexemeTypes("0", NUMBER, EOF);
        assertLexemeTypes("1", NUMBER, EOF);
        assertLexemeTypes("2", NUMBER, EOF);
        assertLexemeTypes("3", NUMBER, EOF);
        assertLexemeTypes("4", NUMBER, EOF);
        assertLexemeTypes("5", NUMBER, EOF);
        assertLexemeTypes("6", NUMBER, EOF);
        assertLexemeTypes("7", NUMBER, EOF);
        assertLexemeTypes("8", NUMBER, EOF);
        assertLexemeTypes("9", NUMBER, EOF);
        assertLexemeTypes("123", NUMBER, EOF);
        assertLexemeTypes("0123456789", NUMBER, EOF);
        assertLexemeTypes("0987654321", NUMBER, EOF);
    }

    @Test
    void nextLexemeSimpleNumberValues() throws Exception {
        assertLexemeValues("0", "0");
        assertLexemeValues("1", "1");
        assertLexemeValues("2", "2");
        assertLexemeValues("3", "3");
        assertLexemeValues("4", "4");
        assertLexemeValues("5", "5");
        assertLexemeValues("6", "6");
        assertLexemeValues("7", "7");
        assertLexemeValues("8", "8");
        assertLexemeValues("9", "9");
        assertLexemeValues("123", "123");
        assertLexemeValues("0123456789", "0123456789");
        assertLexemeValues("9876543210", "9876543210");
    }

}