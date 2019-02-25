package com.example.myapplication;

import java.io.IOException;
import java.io.Reader;

public class Lexer  implements AutoCloseable{
    private int current;
    private Reader reader;

    Lexer(Reader reader) throws IOException {
        this.reader = reader;
        this.current = reader.read();
    }

    public Lexeme LexemeGet() throws Exception {
        while (current == ' ')
            current = reader.read();
        switch (current){
            case  -1:
                return new Lexeme(Type.EOF, "EOF");
            case '+':
                return new Lexeme(Type.PLUS, "+");
            case '-':
                return new Lexeme(Type.MINUS, "-");
            case '*':
                return new Lexeme(Type.MULTIPLY, "*");
            case '/':
                return new Lexeme(Type.DIVIDE, "/");
            case '^':
                return new Lexeme(Type.POWER, "^");
            case '(':
                return new Lexeme(Type.BRACKET_OPEN, "(");
            case ')':
                return new Lexeme(Type.BRACKET_CLOSE, ")");


                default:
                    if (Character.isDigit(current)) {
                        StringBuilder number = new StringBuilder();

                        do {
                            number.append((char) current);
                            current = reader.read();
                        } while (Character.isDigit(current));

                        return new Lexeme(Type.NUMBER, number.toString());
                    } else
                        throw new Exception();
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
