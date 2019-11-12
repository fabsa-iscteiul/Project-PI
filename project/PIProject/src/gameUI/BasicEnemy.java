package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		double rnd1,rnd2,p;
		do {
			rnd1 = Math.random()*2 - 1;
			rnd2 = Math.random()*2 -1;
			p = (rnd1*rnd1 + rnd2 * rnd2)*0.9973 + 0.0027;
		} while (p>=1);
		velX =(float) (5 + rnd1*Math.sqrt(-2*Math.log(p)/p));
		velY =(float) (5 + rnd1*Math.sqrt(-2*Math.log(p)/p));
		this.handler = handler;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
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
		handler.addObject(new Trail((int) x, (int) y, ID.TRAIL, 16, 16, 0.02f, Color.red, handler));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
