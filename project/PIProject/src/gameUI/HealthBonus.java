package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBonus extends GameObject {

	private int heal;
	private double timer;
	private Handler handler;

	public HealthBonus(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		heal = (int) (Math.random() * 40 + 10);
		timer = -Math.log(Math.random());
		if (timer < 2)
			timer = 7 - timer;
		else if (timer > 5)
			timer = 5;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	@Override
	public void tick() {
		timer = timer - ((double) 1 / 60);
		if (timer <= 0) {
			handler.removeObject(this);
		}
	}

	public int getHeal() {
		return heal;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
