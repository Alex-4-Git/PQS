package im.castor.ConnectFour.Model;

public class ComputerPlayer implements IPlayer {
	private Board board;
	private int maxRow = 0;
	private int maxColumn = 0;

	/**
	 * protected. Using for PlayerFactory Only.
	 * @param board input is board object.
	 */
	ComputerPlayer(Board board) {
		this.board = board;
		this.maxColumn = this.board.getBoardDataArray()[0].length;
		this.maxRow = this.board.getBoardDataArray().length;

		System.out.println("Computer Player initialized ...");
	}

	@Override
	public void setMove(int col) {
		throw new UnsupportedOperationException();
	}

	private int isNextStepWin() {
		int moveNumber = -1;
		for (int i = 0; i < maxColumn; i++) {
			if (board.getColumnState(i) < maxRow) {
				board.move(i);

				if (board.isOver()) {
					moveNumber = i;
					board.undoMove();
					break;
				}

				board.undoMove();
			}
		}

		return moveNumber;
	}

	private int isNextStepLose() {
		int moveNumber = -1;
		for (int i = 0; i < maxColumn; i++) {
			if (board.getColumnState(i) < maxRow) {
				board.move(i);
				// Consider for Opponent
				int opponentStep = isNextStepWin();
				if (opponentStep != -1) {
					moveNumber = opponentStep;
					board.undoMove();
					break;
				}

				board.undoMove();
			}
		}
		return moveNumber;
	}

	@Override
	public void play() {
		int moveNumber = -1;

		moveNumber = isNextStepWin();
		moveNumber = isNextStepLose();

		if (moveNumber == -1) {
			moveNumber = (int) (Math.random() * this.maxColumn);

			while (board.getColumnState(moveNumber) >= this.maxRow) {
				moveNumber = (int) (Math.random() * this.maxColumn);
			}
		}

		board.move(moveNumber);
	}

}