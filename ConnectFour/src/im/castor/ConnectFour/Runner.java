package im.castor.ConnectFour;

import im.castor.ConnectFour.Controller.Controller;
import im.castor.ConnectFour.View.ConnectFourGUI;

public class Runner {

	/**
	 * Startup the Whole Game.
	 * @param args
	 */
	public static void main(String[] args) {

		Controller controller = new Controller();
		ConnectFourGUI guiShell = new ConnectFourGUI(controller);

		guiShell.gameReset();
		controller.setGuiView(guiShell);
		controller.setBoard(guiShell.getBoard());
		controller.setPlayerA(guiShell.getPlayer1());
		controller.setPlayerB(guiShell.getPlayer2());

		controller.switchToNextPlayer();
	}
}
