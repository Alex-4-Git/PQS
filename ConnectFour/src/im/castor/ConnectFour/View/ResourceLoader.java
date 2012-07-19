package im.castor.ConnectFour.View;

import javax.swing.ImageIcon;

public class ResourceLoader {

	/**
	 * Load a picture from the file by given color.
	 * @param color
	 * @return ImageIcon type's chess.
	 */
	public ImageIcon getChess(String color) {
		String imagePath;

		if (color.equals("Red")) {
			imagePath = "images/Red.gif";

		} else if (color.equals("White")) {
			imagePath = "images/White.gif";
		} else {
			imagePath = "images/Win.gif";
		}

		ImageIcon chess = new ImageIcon(imagePath);
		return chess;
	}

}
