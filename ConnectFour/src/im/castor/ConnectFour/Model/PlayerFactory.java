package im.castor.ConnectFour.Model;

public class PlayerFactory {
	/**
	 * Generator New Player Factory.
	 * @param playerType "Computer" for creating computer player, "Human" for
	 *            creating human player. by default, it will create human
	 *            player.
	 * @param fromGUI whether the request is from GUI or console.
	 * @return
	 */
	public static IPlayer getPlayer(String playerType, Board board) {
		if (playerType.equals("Computer"))
			return new ComputerPlayer(board);
		else if (playerType.equals("Human"))
			return new HumanPlayer(board);

		return new HumanPlayer(board);
	}

}
