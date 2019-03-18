import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class StateMachine {
    private final Reader reader;
    private Reader word;
    private ArrayList<Integer> endStates = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();

    public StateMachine(Reader reader) {
        this.reader = reader;
    }

    public boolean run() {
        try {
            if (endStates.isEmpty()) parser();
            return process();
        } catch (IOException | StateMachineExeption e) {
            return false;
        }
    }

    private void parser() throws StateMachineExeption {
        Scanner in = new Scanner(reader);
        String[] ends = in.nextLine().split(" ");
        for (String finalCond : ends) {
            endStates.add(Integer.valueOf(finalCond));
        }
        while (in.hasNextLine()) {
            String[] readTranfition = in.nextLine().split(" ");
            if (readTranfition.length != 3)
                throw new StateMachineExeption("Bad transition");
            int startState = Integer.parseInt(readTranfition[0]);
            char symbol = readTranfition[1].charAt(0);
            int endState = Integer.parseInt(readTranfition[2]);

            Transition currentTransition = new Transition(startState, symbol);
            Transition existingTransition = null;
            for (Transition transition : transitions) {
                if (transition.getStartState() == currentTransition.getStartState() &&
                        transition.getSymbol() == currentTransition.getSymbol()) {
                    existingTransition = transition;
                }
            }
            if (existingTransition == null) {
                currentTransition.addEndState(endState);
                transitions.add(currentTransition);
            } else {
                existingTransition.addEndState(endState);
            }

        }
    }

    private boolean process() throws IOException {
        Stack<Configuration> stack = new Stack<>();
        stack.push(new Configuration(0, 0));
        while (!stack.isEmpty()) {
            Configuration configuration = stack.pop();
            try {
                getNextSymbol();
            } catch (EOFException e) {
                for (Integer endState : endStates) {
                    if (endState == configuration.getCurrentState())
                        return true;
                }
                continue;
            }
            for (Transition transition : transitions) {
                if (transition.getStartState() == (configuration.getCurrentState())) {
                    ArrayList<Integer> endStates = transition.getEndStates();
                    endStates.forEach(
                            endState -> stack.push(
                                    new Configuration(endState, configuration.getCurrentIndex() + 1)));
                }
            }
        }
        return false;
    }

    public void setWord(Reader word) {
        this.word = word;
    }

    private char getNextSymbol() throws IOException {
        int read = word.read();
        if (read == -1)
            throw new EOFException();
        return (char) read;
    }
}

