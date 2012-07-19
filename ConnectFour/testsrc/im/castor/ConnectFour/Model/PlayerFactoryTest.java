package im.castor.ConnectFour.Model;

import static org.junit.Assert.*;
import im.castor.ConnectFour.Model.Board;
import im.castor.ConnectFour.Model.ComputerPlayer;
import im.castor.ConnectFour.Model.HumanPlayer;
import im.castor.ConnectFour.Model.IPlayer;
import im.castor.ConnectFour.Model.PlayerFactory;

import org.junit.Test;


public class PlayerFactoryTest {
	private IPlayer playerTest;

	/**
	 * Test for creating a human player.
	 */
	@Test
	public void factoryCreatingHumanPlayerTest() {
		playerTest = PlayerFactory.getPlayer("Human", null);
		assertTrue(playerTest instanceof HumanPlayer);
		assertFalse(playerTest instanceof ComputerPlayer);
	}

	/**
	 * Test for creating a computer player.
	 */
	@Test
	public void factoryCreatingComputerPlayerTest() {
		playerTest = PlayerFactory.getPlayer("Computer", Board.getInstance(3, 2));
		assertTrue(playerTest instanceof ComputerPlayer);
		assertFalse(playerTest instanceof HumanPlayer);
	}

	/**
	 * Test for creating an arbitrary player, should return human player.
	 */
	@Test
	public void factoryCreatingArbitraryPlayerTest() {
		playerTest = PlayerFactory.getPlayer("AnyString", null);
		assertTrue(playerTest instanceof HumanPlayer);
		assertFalse(playerTest instanceof ComputerPlayer);
	}
}
