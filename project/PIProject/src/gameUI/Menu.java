package gameUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameUI.Game.STATE;

public class Menu extends MouseAdapter {
	private Handler handler;
	private Game game;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void render(Graphics g) {
		Font font = new Font("arial", 1, 50);
		Font font2 = new Font("arial", 1, 30);
		if (game.getGameState() == STATE.Menu) {
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Squares", 210, 70);
			g.setFont(font2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		} else if (game.getGameState() == STATE.Help) {
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Help", 240, 70);
			g.setFont(font2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
			g.setFont(new Font("arial", 1, 20));
			g.drawString("Use the arrow keys to move player", 10, 125);
			g.drawString("Dodge the enemies in order to survive", 10, 150);

		} else if (game.getGameState() == STATE.End) {
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 175, 70);
			g.setFont(font2);
			g.drawString("Score: " + game.getHud().getScore(), 230, 130);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);

		}
	}

	public void tick() {

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.getGameState() == STATE.Menu) {
			// QUIT
			if (mouseOver(mx, my, 210, 350, 200, 64))
				System.exit(1);
			// HELP
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.setGameState(STATE.Help);
			}
			// PLAY
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.setGameState(STATE.Game);
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.PLAYER, handler));
				game.getSpawn().tick();
			}
		} else if (game.getGameState() == STATE.Help) {
			// BACK
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.setGameState(STATE.Menu);
			}
		} else if (game.getGameState() == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				// TRY AGAIN
				game.setGameState(STATE.Game);
				HUD.HEALTH = 100;
				game.getHud().setScore(0);
				game.getHud().setLevel(1);
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.PLAYER, handler));
				handler.addObject(new BasicEnemy((int) (Math.random() * 614 + 16), (int) (Math.random() * 450 + 16),
						ID.BASICENEMY, handler));
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height)
				return true;
			return false;
		} else
			return false;
	}
}
