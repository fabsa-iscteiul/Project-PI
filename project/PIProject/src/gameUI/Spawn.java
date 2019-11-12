package gameUI;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private BonusHandler bonusHandler;
	private int runs = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		bonusHandler = new BonusHandler(this.handler, this);
		bonusHandler.start();
	}

	public synchronized void tick() { // ASSIM A CADA 150 DE SCORE ACRESCENTA INIMIGO
		if (HUD.HEALTH <= 50 && runs == 0) {
			bonusHandler.interrupt();
			runs = 1;
		}
		if (hud.getScore() % 150 == 0 || hud.getScore() == 0) {
			hud.setLevel(hud.getLevel() + 1);
			double module = 150
					+ (20 * Math.sqrt(-2 * Math.log(Math.random()))) * Math.cos(2 * Math.PI * Math.random());
			double angle = Math.random() * 360;
			int x = (int) (module * Math.cos(Math.toRadians(angle))) + 320;
			int y = (int) (module * Math.sin(Math.toRadians(angle))) + 240;
			double enemy = Math.random();
			if (enemy <= 0.4)
				handler.addObject(new BasicEnemy(x, y, ID.BASICENEMY, handler));
			else if (enemy > 0.4 && enemy <= 0.65)
				handler.addObject(new FastXEnemy(x, y, ID.FASTXENEMY, handler));
			else if (enemy > 0.65 && enemy <= 0.85)
				handler.addObject(new FastYEnemy(x, y, ID.FASTYENEMY, handler));
			else if (enemy > 0.85)
				handler.addObject(new SmartEnemy(x, y, ID.SMARTENEMY, handler));
		}

	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public Handler getHandler() {
		return handler;
	}

	public HUD getHud() {
		return hud;
	}
}
