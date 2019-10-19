package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.PLAYER)
				player = handler.object.get(i);
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math
				.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		velX = ((-1 / distance) * diffX);
		velY = ((-1 / distance) * diffY);
		handler.addObject(new Trail((int) x, (int) y, ID.TRAIL, 16, 16, 0.02f, Color.cyan, handler));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
