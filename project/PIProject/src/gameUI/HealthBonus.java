package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBonus extends GameObject {

	private int heal;

	public HealthBonus(int x, int y, ID id) {
		super(x, y, id);
		heal = (int) (Math.random() * 40 + 10);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	public int getHeal() {
		return heal;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
