public class State {

    private final int id;
    private final boolean isFinal;

    State(int id, boolean isFinal) {
        this.id = id;
        this.isFinal = isFinal;
    }

    public int getId() {
        return id;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
