package guessnumber;

import java.util.ArrayList;
import java.util.List;


public class GuessNum {

    private int num;
    private int maxNum = 100;

    private List<IGameListener> listeners = new ArrayList<IGameListener>();

    public void addGameListener(IGameListener gameListener) {
        listeners.add(gameListener);
    }

    public void removeGameListener(IGameListener gameListener) {
        listeners.remove(gameListener);
    }

    public GuessNum() {
    }

    public void startGame() {
        reset();
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void reset() {
        num = (int) (Math.random() * maxNum);
        fireGameStartedEvent();
    }

    public void makeGuess(int guess) {
        fireGuessMadeEvent(guess);
        if (guess == num) {
            fireWinEvent();
        } else if (guess < num) {
            fireTooLowEvent();
        } else {
            fireTooHighEvent();
        }
    }

    // Event firing methods.
    private void fireGameStartedEvent() {
        for (IGameListener listener : listeners) {
            listener.gameStarted();
        }
    }

    private void fireGuessMadeEvent(int num) {
        for (IGameListener listener : listeners) {
            listener.guessMade(num);
        }
    }

    private void fireTooHighEvent() {
        for (IGameListener listener : listeners) {
            listener.tooHigh();
        }
    }

    private void fireTooLowEvent() {
        for (IGameListener listener : listeners) {
            listener.tooLow();
        }
    }

    private void fireWinEvent() {
        for (IGameListener listener : listeners) {
            listener.win();
        }
    }

}
