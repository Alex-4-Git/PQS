package im.castor.ConnectFour.Model;

import static org.junit.Assert.*;
import im.castor.ConnectFour.Model.Board;
import im.castor.ConnectFour.Model.BoardBuilder;

import org.junit.Test;


public class BoardBuilderTest {

	/**
	 * Test the board builder to verify whether can generate a new board.
	 */
	@Test
	public void boardBuilderTest() {
		Board board = new BoardBuilder().withCol(7).withRow(6).build();
		assertTrue(board instanceof Board);
	}

}
