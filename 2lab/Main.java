import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: [STATES_INFO_FILE] [INPUT_FILE]");
            System.exit(1);
        }

        StatesInfo statesInfo = null;
        try {
            statesInfo = StateParser.parseFrom(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println(String.format("File \"%s\" not found", args[0]));
            System.exit(1);
        }

        StateMachine stateMachine = new StateMachine(statesInfo);
        boolean result = false;
        try {
            result = stateMachine.isValidInput(new FileReader(args[1]));
        } catch (FileNotFoundException e) {
            System.out.println(String.format("File \"%s\" not found", args[0]));
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (result) {
            System.out.println("Input valid!");
        } else {
            System.out.println("Input invalid!");
        }
    }
}

