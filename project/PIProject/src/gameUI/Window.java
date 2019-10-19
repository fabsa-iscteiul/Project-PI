package gameUI;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Window extends Canvas {

	private static final long serialVersionUID = 8400983715393400831L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		Dimension dim = new Dimension(width, height);
		frame.setPreferredSize(dim);
		frame.setMaximumSize(dim);
		frame.setMinimumSize(dim);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
