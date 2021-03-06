package gameUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	public enum STATE {
		Menu, Help, Game, End;
	};

	private STATE gameState = STATE.Menu;
	private Menu menu;
	private static final long serialVersionUID = 3634038279619051963L;
	public static final int WIDTH = 640, HEIGHT = 480;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Squares", this);
		this.addMouseListener(menu);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			System.out.println("Failed to stop");
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				render();
				delta--;
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;

			}

		}
		stop();
	}

	private void tick() {
		if (HUD.HEALTH <= 0 && gameState != STATE.End) {
			gameState = STATE.End;
			handler.object.clear();
		}
		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();
			spawn.tick();
		} else
			menu.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		if (gameState == STATE.Game)
			hud.render(g);
		else
			menu.render(g);
		g.dispose();
		bs.show();

	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public HUD getHud() {
		return hud;
	}

	public STATE getGameState() {
		return gameState;
	}

	public void setGameState(STATE gameState) {
		this.gameState = gameState;
	}

	public Spawn getSpawn() {
		return spawn;
	}

	public static void main(String[] args) {
		new Game();
	}
}
