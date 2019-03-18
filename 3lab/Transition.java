import java.util.ArrayList;

class Transition {
    private int startState;
    private ArrayList<Integer> endStates = new ArrayList<>();
    private char symbol;
    void addEndState(Integer endState){
        endStates.add(endState);
    }

    public Transition(int startState, char symbol){
        this.symbol = symbol;
        this.startState = startState;
    }

    public int getStartState() {
        return startState;
    }

    public ArrayList<Integer> getEndStates() {
        return endStates;
    }

    public char getSymbol() {
        return symbol;
    }
}
