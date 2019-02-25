import java.util.Map;
import java.util.Set;

import static java.lang.System.exit;

public class StatesInfo {

    private Map<Integer, Map<Character, Integer>> transitions;
    private Set<Integer> finalStates;
    private State currentState;

    public StatesInfo(Map<Integer, Map<Character, Integer>> transitions, Set<Integer> finalStates) {
        this.transitions = transitions;
        this.finalStates = finalStates;

        currentState = new State(1, finalStates.contains(1));
        if (!transitions.containsKey(1)) {
            System.out.println("no transits for first state");
            exit(1);
        }
    }

    public void nextState(char transition) {
        Map<Character, Integer> targets = transitions.get(currentState.getId());
        if (targets == null) {
            return;
        }
        Integer nextState = targets.get(transition);
        if (nextState == null) {
            return;
        }
        currentState = new State(nextState, finalStates.contains(nextState));
    }

    public State getLastState() {
        return currentState;
    }
}
