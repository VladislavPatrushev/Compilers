package com.example.myapplication;

public class Lexeme {
    final private Type type;
    final private String text;

    Lexeme(Type type, String text){
        this.text = text;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
