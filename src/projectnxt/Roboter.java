package projectnxt;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import projectnxt.strategy.Strategy;

/**
 * Roboter controller class
 * 
 * @author alexandervetter
 */
public class Roboter implements Runnable {
	protected Strategy strategy;
	protected final DifferentialPilot pilot;
	
	public Roboter(final Strategy strategy, final DifferentialPilot pilot) {
		this.strategy = strategy;
		this.pilot = pilot;
		
		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				LCD.drawString("Stopping...", 0, 0);
				pilot.stop();
			}

			public void buttonReleased(Button b) {
				LCD.clear();
				System.exit(0);
			}
		});
	}
		
	public void run() {
		LCD.drawString("Press to start...", 0, 0);
		Button.ENTER.waitForPress();
		LCD.clearDisplay();
		
		while(this.strategy != null) {
			this.strategy.setRoboter(this);
			this.strategy.run();
			this.strategy = this.strategy.getNextStrategy();
		}
	}
	
	public DifferentialPilot getPilot() {
		return this.pilot;
	}
	
	public static void main(String... args) {
		final Strategy main = StrategyFactory.makeQuadratMain();
		//final Strategy main = StrategyFactory.makePledgeMain();
		
		final DifferentialPilot pilot = new DifferentialPilot(3.0f, 15f, Motor.A, Motor.C, false);
		
		Roboter bot = new Roboter(main, pilot);
		
		bot.run();
	}
}
