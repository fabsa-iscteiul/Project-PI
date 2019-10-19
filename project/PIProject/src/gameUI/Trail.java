package gameUI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private float alpha = 1, life; // MAIS VIDA SIGNIFICA MAIOR TRAIL
	private Handler handler;
	private Color color;
	private int width, height;

	public Trail(int x, int y, ID id, int width, int height, float life, Color color, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	private AlphaComposite makeTransparent(float a) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, a);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
		g2d.setComposite(makeTransparent(1));
	}

	@Override
	public void tick() {
		if (alpha > life)
			alpha -= life - 0.001f;
		else
			handler.removeObject(this);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
