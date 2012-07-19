package im.castor.ConnectFour.Model;

public interface IPlayer {
	/**
	 * setMove method created to distinguish human from computer. As to Human
	 * Player, In terminal, setMove(-2) always being called. In GUI, it plays
	 * same as Computer Player. As to Computer Player, col is the step Computer
	 * Player generated.
	 * @param col
	 */
	public void setMove(int col);

	/**
	 * Use the implemented strategy to move a step in the board.
	 */
	public void play();
}