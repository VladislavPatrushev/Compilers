public class Configuration {
    private int currentState;
    private int currentIndex;

    public Configuration(int currentIndex, int currentState){
        this.currentIndex = currentIndex;
        this.currentState = currentState;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getCurrentState() {
        return currentState;
    }
}
