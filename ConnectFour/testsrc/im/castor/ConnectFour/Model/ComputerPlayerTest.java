package im.castor.ConnectFour.Model;

import static org.junit.Assert.*;
import im.castor.ConnectFour.Model.Board;
import im.castor.ConnectFour.Model.BoardBuilder;
import im.castor.ConnectFour.Model.ComputerPlayer;

import org.junit.Before;
import org.junit.Test;


public class ComputerPlayerTest {
	private ComputerPlayer player;
	private Board board;

	/**
	 * Setup
	 * @throws Exception Nothing.
	 */
	@Before
	public void setUp() throws Exception {
		board = new BoardBuilder().withCol(7).withRow(6).build();
		player = new ComputerPlayer(board);
	}

	/**
	 * Once called setMove method, it should throw an
	 * UnsupportedOperationException.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void setMoveTest() {
		player.setMove(0);
	}

	/**
	 * Test for can win in next step.
	 */
	@Test
	public void canWinTest() {
		for (int i = 0; i < 3; i++) {
			board.move(0);
			board.move(1);
		}

		board.move(2);

		for (int i = 0; i < 3; i++) {
			board.move(0);
		}

		board.move(1);
		board.move(2);
		board.move(5);

		player.play();
		int expected = 1;
		int actual = board.getBoardDataArray()[3][0];
		assertEquals(expected, actual);
	}

	/**
	 * Test for prevent lose in next step.
	 */
	@Test
	public void preventLoseTest() {
		for (int i = 0; i < 3; i++) {
			board.move(0);
			board.move(1);
		}
		board.move(1);
		player.play();

		int expected = 2;
		int actual = board.getBoardDataArray()[2][0];
		assertEquals(expected, actual);
	}

	/**
	 * Test for arbitrary move in next step.
	 */
	@Test
	public void randomMoveTest() {
		player.play();
		assertFalse(board.isOver());
	}

	/**
	 * Test for the column full situation, generate a new move.
	 */
	@Test
	public void playIfColumnFullTest() {
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 3; i++) {
				board.move(i);
			}
		}
		for (int j = 0; j < 6; j++) {
			for (int i = 3; i < 6; i++) {
				board.move(i);
			}
		}
		player.play();
		assertFalse(board.isOver());
	}

}
