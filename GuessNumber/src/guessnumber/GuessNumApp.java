package guessnumber;

public class GuessNumApp {
    
    private void start() {
        GuessNum guessNum = new GuessNum();
        @SuppressWarnings("unused")
        GuessNumFrame frame = new GuessNumFrame(guessNum);
        guessNum.startGame();
    }
    
    public static void main(String[] arg) {
        GuessNumApp app = new GuessNumApp();
        app.start();
    }
}
