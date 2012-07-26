package guessnumber;

public interface IGameListener {
    public void guessMade(int num);
    public void gameStarted();
    public void tooHigh();
    public void tooLow();
    public void win();
}
