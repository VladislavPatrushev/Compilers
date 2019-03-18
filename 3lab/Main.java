import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        StateMachine machine = new StateMachine(
                new StringReader("0 1\n" +
                        "0 a 1\n" +
                        "0 b 2\n" +
                        "1 c 3\n" +
                        "3 a 0\n"));
        machine.setWord(new StringReader("acab"));
        System.out.println("Распознается ли? \n" + machine.run());
    }
}
