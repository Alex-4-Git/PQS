package im.castor.ConnectFour.View;

import im.castor.ConnectFour.Controller.*;
import im.castor.ConnectFour.Model.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class ConnectFourGUI implements ActionListener, ItemListener {

	private int maxRow = 6;
	private int maxColumn = 7;
	private Board board;
	private IPlayer player1;
	private IPlayer player2;
	private Controller controller;
	private ResourceLoader resourceLoader;

	private JFrame frameMainWindow;
	private JPanel panelBoardNumbers;
	private JLayeredPane layeredGameBoard;

	/**
	 * Initialize the GUI
	 * @param controller
	 */
	public ConnectFourGUI(Controller controller) {
		board = new BoardBuilder().withCol(maxColumn).withRow(maxRow).build();
		player1 = PlayerFactory.getPlayer("Human", board);
		player2 = PlayerFactory.getPlayer("Human", board);
		this.controller = controller;
	}

	/**
	 * @return the board;
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return player1
	 */
	public IPlayer getPlayer1() {
		return player1;
	}

	/**
	 * @return player2
	 */
	public IPlayer getPlayer2() {
		return player2;
	}

	private JMenuBar generateMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;

		// create and build first menu
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		// add items to menu
		menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
		menuItem.setName("NewGame");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		menuItem.setName("QuitGame");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// create and build second menu
		menu = new JMenu("Players");
		menu.setMnemonic(KeyEvent.VK_P);
		menuBar.add(menu);
		// add items to menu
		submenu = new JMenu("Player 1");
		ButtonGroup groupPlayers1 = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("Human Player");
		if (player1 instanceof HumanPlayer) {
			rbMenuItem.setSelected(true);
		}
		rbMenuItem.setName("P1Human");
		rbMenuItem.addItemListener(this);
		groupPlayers1.add(rbMenuItem);
		submenu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Computer Player");
		if (player1 instanceof ComputerPlayer) {
			rbMenuItem.setSelected(true);
		}
		rbMenuItem.setName("ComputerPlayer1");
		rbMenuItem.addItemListener(this);
		groupPlayers1.add(rbMenuItem);
		submenu.add(rbMenuItem);
		menu.add(submenu);

		// submenu 2
		submenu = new JMenu("Player 2");
		ButtonGroup groupPlayers2 = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("Computer Player");
		if (player2 instanceof ComputerPlayer)
			rbMenuItem.setSelected(true);
		rbMenuItem.setName("ComputerPlayer2");
		rbMenuItem.addItemListener(this);
		groupPlayers2.add(rbMenuItem);
		submenu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Human");
		if (player1 instanceof HumanPlayer)
			rbMenuItem.setSelected(true);
		rbMenuItem.setName("P2Human");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_H);
		groupPlayers2.add(rbMenuItem);
		rbMenuItem.addItemListener(this);
		submenu.add(rbMenuItem);
		menu.add(submenu);

		return menuBar;
	}

	private JLayeredPane createLayeredBoard() {
		layeredGameBoard = new JLayeredPane();
		layeredGameBoard.setPreferredSize(new Dimension(570, 490));

		return layeredGameBoard;
	}

	private void drawChess(int row, int column, String color) {
		int xOffset = 75 * column;
		int yOffset = 75 * row;
		ImageIcon icon = resourceLoader.getChess(color);
		JLabel iconLabel = new JLabel(icon);
		iconLabel.setBounds(27 + xOffset, 27 + yOffset, icon.getIconWidth(), icon.getIconHeight());
		layeredGameBoard.add(iconLabel, new Integer(0), 0);
		frameMainWindow.paint(frameMainWindow.getGraphics());
	}

	/**
	 * For the controller to repaint the Board once needed.
	 */
	public void boardRepaint() {
		int[][] boardView = board.getBoardDataArray();
		int row = board.getMoveX();
		int column = board.getMoveY();
		int playerPos = boardView[row][column];

		String color = (playerPos == 1) ? ("Red") : ("White");
		drawChess(row, column, color);

	}

	private Component createContentComponents() {

		// create panels to hold and organize board numbers
		panelBoardNumbers = new JPanel();
		panelBoardNumbers.setLayout(new GridLayout(1, 7, 4, 4));

		JButton[] buttonCol = new JButton[maxColumn];
		for (int i = 0; i < buttonCol.length; i++) {
			buttonCol[i] = new JButton(Integer.toString(i));

			buttonCol[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					String text = ((JButton) event.getSource()).getText();
					try{
					controller.humanPlay(Integer.parseInt(text));
					} catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
					}
				
				}
			});

			panelBoardNumbers.add(buttonCol[i]);
		}

		// create game board with pieces
		layeredGameBoard = createLayeredBoard();

		// create panel to hold all of above
		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout());
		panelMain.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// add objects to pane
		panelMain.add(panelBoardNumbers, BorderLayout.NORTH);
		panelMain.add(layeredGameBoard, BorderLayout.CENTER);

		return panelMain;
	}

	/**
	 * actionPerform for menu New Game and Quit Game.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String sourceText = source.getName();

		if (sourceText.equals("NewGame")) {
			gameReset();
			controller.switchToNextPlayer();
		} else if (sourceText.equals("QuitGame")) {
			System.exit(0);
		}
	}

	/**
	 * Change player1 and player2 in the middle of the game.
	 */
	public void itemStateChanged(ItemEvent event) {
		JMenuItem source = (JMenuItem) (event.getSource());
		String eventName = source.getName();

		if (event.getStateChange() == ItemEvent.SELECTED) {

			if (eventName.equals("ComputerPlayer1")) {
				player1 = PlayerFactory.getPlayer("Computer", board);
			} else if (eventName.equals("P1Human")) {
				player1 = PlayerFactory.getPlayer("Human", board);
			} else if (eventName.equals("ComputerPlayer2")) {
				player2 = PlayerFactory.getPlayer("Computer", board);
			} else if (eventName.equals("P2Human")) {
				player2 = PlayerFactory.getPlayer("Human", board);
			}

			gameReset();
			controller.setPlayerA(player1);
			controller.setPlayerB(player2);
			controller.setCurrentPlayer(player2);
			controller.switchToNextPlayer();
		}

	}

	/**
	 * Once game over, set the win step and pop out a window.
	 */
	public void gameOver() {
		this.panelBoardNumbers.setVisible(false);
		this.panelBoardNumbers.repaint();

		java.util.List<Board.Coordinate> winStep = board.getWinStep();
		if (winStep.size() >= 4) {
			for (Board.Coordinate i : winStep) {
				int drawX = i.getX();
				int drawY = i.getY();
				this.drawChess(drawX, drawY, "Win");
			}
		}

		String msg;
		if (board.getWinnerNumber() == 1) {
			msg = "Player 1 (Red) wins!";
		} else if (board.getWinnerNumber() == 2) {
			msg = "Player 2 (White) wins!";
		} else {
			msg = "Tie! No one wins.";
		}

		JOptionPane.showMessageDialog(null, msg, "Winner State", JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * reset the GUI style.
	 */
	public void gameReset() {
		board = new BoardBuilder().withCol(maxColumn).withRow(maxRow).build();
		resourceLoader = new ResourceLoader();

		if (frameMainWindow != null) {
			frameMainWindow.dispose();
		}
		frameMainWindow = new JFrame("im.castor - Connect Four");
		frameMainWindow.setBounds(100, 100, 400, 300);

		frameMainWindow.setJMenuBar(generateMenuBar());
		Component compMainWindowContents = createContentComponents();
		frameMainWindow.getContentPane().add(compMainWindowContents, BorderLayout.CENTER);

		panelBoardNumbers.setVisible(true);
		panelBoardNumbers.repaint();

		frameMainWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// connectFour show
		frameMainWindow.pack();
		frameMainWindow.setVisible(true);
	}

}
