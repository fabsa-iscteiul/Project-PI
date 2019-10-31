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
			double enemy = Math.random();
			if (enemy <= 0.4) {
				double angleRandom = Math.random();
				int angle = (int) (angleRandom*360);
				double firstRandom = Math.random();
				double secondRandom = Math.random();				
				int module = (int) (150 + (20 * Math.sqrt(-2 * Math.log(firstRandom))) * Math.cos(2 * Math.PI * secondRandom));
				int X = (int) (module * Math.cos (angle)) + 320;
				System.out.println("Valor do X: " + X);
				handler.addObject(new BasicEnemy((int) (X), (int) (Math.random() * 450 + 16),
						ID.BASICENEMY, handler));}
			else if (enemy > 0.4 && enemy <= 0.65)
				handler.addObject(new FastXEnemy(300, 200, ID.FASTXENEMY, handler));
			else if (enemy > 0.65 && enemy <= 0.85)
				handler.addObject(new FastYEnemy(300, 200, ID.FASTYENEMY, handler));
			else if (enemy > 0.85)
				handler.addObject(new SmartEnemy(300, 200, ID.SMARTENEMY, handler));
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
