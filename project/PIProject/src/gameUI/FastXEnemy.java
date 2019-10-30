package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastXEnemy extends GameObject {

	private Handler handler;

	public FastXEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = (float) (7 + ((Math.sqrt(-2 * Math.log(Math.random()))) * Math.cos(2 * Math.PI * Math.random())));
		velY = (float) (5 + ((Math.sqrt(-2 * Math.log(Math.random()))) * Math.cos(2 * Math.PI * Math.random())));
		this.handler = handler;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
		handler.addObject(new Trail((int) x, (int) y, ID.TRAIL, 16, 16, 0.02f, Color.blue, handler));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
