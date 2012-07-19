package im.castor.ConnectFour.Controller;

import im.castor.ConnectFour.Model.Board;
import im.castor.ConnectFour.Model.ComputerPlayer;
import im.castor.ConnectFour.Model.IPlayer;
import im.castor.ConnectFour.View.*;

public class Controller {

	private ConnectFourGUI guiView;
	private IPlayer playerA;
	private IPlayer playerB;
	private Board board;
	private IPlayer currentPlayer;

	public void setCurrentPlayer(IPlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setGuiView(ConnectFourGUI guiView) {
		this.guiView = guiView;
	}

	public void setPlayerA(IPlayer playerA) {
		this.playerA = playerA;
	}

	public void setPlayerB(IPlayer playerB) {
		this.playerB = playerB;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Constructor. Set currentPlayer.
	 */
	public Controller() {
		currentPlayer = playerB;
	}

	/**
	 * Execute one step moving by human.
	 * @param col The column get from ActionListener
	 */
	public void humanPlay(int col) {
		currentPlayer.setMove(col);
		currentPlayer.play();
		guiView.boardRepaint();
		switchToNextPlayer();
	}

	/**
	 * Always switch to next Player.
	 */
	public void switchToNextPlayer() {
		if (board.isOver()) {
			guiView.gameOver();
		} else {
			currentPlayer = (currentPlayer == playerA) ? playerB : playerA;
			if (currentPlayer instanceof ComputerPlayer) {
				computerPlay();
			}
		}
	}

	/**
	 * Execute one step moving by computer.
	 */
	private void computerPlay() {
		currentPlayer.play();
		guiView.boardRepaint();
		switchToNextPlayer();
	}

}