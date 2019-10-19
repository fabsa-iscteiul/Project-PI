package gameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	private Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp((int) x, 0, Game.WIDTH - 37);
		y = Game.clamp((int) y, 0, Game.HEIGHT - 67);
		handler.addObject(new Trail((int) x, (int) y, ID.TRAIL, 32, 32, 0.05f, Color.white, handler));
		collision();
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BASICENEMY || tempObject.getId() == ID.SMARTENEMY
					|| tempObject.getId() == ID.FASTYENEMY || tempObject.getId() == ID.FASTXENEMY)
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			if (tempObject.getId() == ID.BONUS)
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH += ((HealthBonus) tempObject).getHeal();
					handler.removeObject(tempObject);
				}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
