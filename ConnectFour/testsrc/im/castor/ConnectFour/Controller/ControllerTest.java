package im.castor.ConnectFour.Controller;

import static org.junit.Assert.*;

import im.castor.ConnectFour.Controller.Controller;
import im.castor.ConnectFour.Model.Board;
import im.castor.ConnectFour.Model.IPlayer;
import im.castor.ConnectFour.Model.PlayerFactory;
import im.castor.ConnectFour.View.ConnectFourGUI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;


public class ControllerTest {

	private Board board;
	private IPlayer player1;
	private IPlayer player2;
	private ConnectFourGUI guiShell;
	private Controller controller;

	/**
	 * Setup for the test.
	 * @throws Exception Nothing.
	 */
	@Before
	public void setUp() throws Exception {
		controller = new Controller();
		guiShell = new ConnectFourGUI(controller);
		guiShell.gameReset();
		board = guiShell.getBoard();
		player1 = guiShell.getPlayer1();
		player2 = guiShell.getPlayer2();
		controller.setGuiView(guiShell);
		controller.setBoard(guiShell.getBoard());
		controller.setPlayerA(guiShell.getPlayer1());
		controller.setPlayerB(guiShell.getPlayer2());

	}

	/**
	 * Test the human player's move.
	 */
	@Test
	public void humanPlayTest() {
		controller.setCurrentPlayer(player1);
		controller.humanPlay(0);
		int actual = board.getBoardDataArray()[5][0];
		int expected = 1;
		assertEquals(expected, actual);
	}

	/**
	 * Test computer player's move.
	 * @throws SecurityException Should not be thrown.
	 * @throws NoSuchMethodException Should not be thrown.
	 * @throws IllegalArgumentException Should not be thrown.
	 * @throws IllegalAccessException Should not be thrown.
	 * @throws InvocationTargetException Should not be thrown.
	 */
	@Test
	public void computerPlayTest() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
	        IllegalAccessException, InvocationTargetException {
		player2 = PlayerFactory.getPlayer("Computer", board);
		controller.setPlayerA(player2);
		controller.setPlayerB(player1);
		controller.setCurrentPlayer(player2);

		Method method = controller.getClass().getDeclaredMethod("computerPlay", new Class[] {});
		method.setAccessible(true);
		method.invoke(controller, new Object[] {});

		boolean isNotNull = false;
		for (int i = 0; i < 6; i++) {
			if (board.getBoardDataArray()[5][i] != 0) {
				isNotNull = true;
				break;
			}
		}

		assertTrue(isNotNull);
	}

	/**
	 * Test the switchToNextPlayer method whether it can converge to the end.
	 */
	@Test
	public void switchToNextPlayerTest() {
		player1 = PlayerFactory.getPlayer("Computer", board);
		player2 = PlayerFactory.getPlayer("Computer", board);
		controller.setPlayerA(player1);
		controller.setPlayerB(player2);
		controller.setCurrentPlayer(player2);
		controller.switchToNextPlayer();

		assertTrue(board.isOver());
	}

}
