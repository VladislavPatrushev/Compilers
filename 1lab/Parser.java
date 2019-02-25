package com.example.myapplication;

public class Parser {

    private  Lexeme current;
    private Lexer lexer;

    Parser(Lexer lexer) throws Exception {
        this.lexer = lexer;
        this.current = lexer.LexemeGet();
        System.out.print(current.getType());
        System.out.print(current.getText());
    }

    private Lexeme getCurrentLexeme(){
        Lexeme current = this.current;
        System.out.print("current lexeme-");
        System.out.print(current.getText());
        return current;
    }
    private void updapeLexeme() throws Exception {
        this.current = lexer.LexemeGet();
    }

    public int executeCalculations() throws Exception {
        int result = parseExpr();
        if (current.getType() != Type.EOF){
            System.out.print("End of File");
            throw new Exception();
        }

        return result;
    }

    public int parseExpr() throws Exception {
        int temp = parseTerm();
        while ((current.getType() == Type.PLUS)|| (current.getType() == Type.MINUS)){
            Lexeme current = getCurrentLexeme();
            updapeLexeme();

            if( current.getType() == Type.MINUS){
                temp -= parseTerm();
            }
            else{
                temp += parseTerm();
            }
        }
        return temp;
    }
    public int parseTerm() throws Exception {
        int temp = parseFactor();

        while ((current.getType() == Type.DIVIDE)|| (current.getType() == Type.MULTIPLY)){
            Lexeme current = getCurrentLexeme();
            updapeLexeme();

            if( current.getType() == Type.DIVIDE){
                temp /=  parseFactor();
            }
            else{
                temp *= parseFactor();
            }
        }
        return temp;

    }

    public int parseFactor() throws Exception {

        int temp = parsePower();
        if(current.getType() == Type.POWER){
            Lexeme current = getCurrentLexeme();
            updapeLexeme();
            return (int) Math.pow(temp,parseFactor());
        }
        return temp;
    }
    public int parsePower() throws Exception {
        if (current.getType() == Type.MINUS) {
                updapeLexeme();
            return -parseAtom();
        }
        return parseAtom();

    }
    public int parseAtom() throws Exception {
        if (current.getType() == Type.BRACKET_OPEN) {
            updapeLexeme();
            int temp = parseExpr();
            if (current.getType() == Type.BRACKET_CLOSE) {
                updapeLexeme();
                return temp;
            }
        }
        else if (current.getType() == Type.NUMBER) {
            int value = 0;
            try {
                value = Integer.parseInt(current.getText());
            } catch (NumberFormatException e) {
                System.out.print("Very long number");
                throw new Exception();

            }
            updapeLexeme();
            return value;
        }
            System.out.print("Wrong Lexeme");
            throw new Exception();
    }
}
