package gameUI;

public class BonusHandler extends Thread {

	private Handler handler;
	private Spawn spawn;

	public BonusHandler(Handler handler, Spawn spawn) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
		this.spawn = spawn;
	}

	@Override
	public void run() {
		while (true) {
			bonusAppears();
		}

	}

	private void bonusAppears() {
		try {
			sleep(1000000);
		} catch (InterruptedException e) {
			double x = Math.random();
			System.out.println(x);
			if (x < 0.4)
				handler.addObject(new HealthBonus(100, 100, ID.BONUS));
		}
		try {
			sleep(5000);
			spawn.setRuns(0);
		} catch (InterruptedException e1) {
		}
	}

}