import java.io.Reader;
import java.util.*;

public class StateParser {

    public static StatesInfo parseFrom(Reader reader) {
        Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();
        Set<Integer> finalStates = new HashSet<>();
        Scanner scanner = new Scanner(reader);

        String[] states = scanner.nextLine().split("[ ]");
        try{
            for (String str : states) {
                if (!str.isEmpty()) {
                    finalStates.add(Integer.parseInt(str));
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("It is't a final state (bad symbol)");
        }

        try{
            while (scanner.hasNextLine()) {
                int from = scanner.nextInt();
                String str = scanner.next("[a-zA-Z]+");
                int to = scanner.nextInt();
                Map<Character, Integer> targets = transitions.computeIfAbsent(from, k -> new HashMap<>());
                for (char c : str.toCharArray()) {
                    targets.put(c, to);
                }
            }
        }catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Bad transit line");
        }

        return new StatesInfo(transitions, finalStates);
    }
}
