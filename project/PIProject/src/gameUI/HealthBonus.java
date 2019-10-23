package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBonus extends GameObject {

	private int heal;
	private int timer;
	private Handler handler;

	public HealthBonus(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		heal = (int) (Math.random() * 40 + 10);
		timer = 120;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	@Override
	public void tick() {
		timer--;
		System.out.println(timer);
		if (timer <= 0) {
			System.out.println("removi");
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
