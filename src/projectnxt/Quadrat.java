package projectnxt;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Quadrat {
	static final DifferentialPilot pilot = new DifferentialPilot(3.4f, 15.75f, Motor.A, Motor.C, false);
	
	public static void main(String[] args) throws Exception {
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
		
		loop();
	} // main

	public static void loop() throws Exception {
		LCD.drawString("Ready...", 0, 0);
		Button.ENTER.waitForPress();
		
		pilot.setRotateSpeed(50);
		//pilot.setAcceleration(1);
		//pilot.forward();
		
		while (true) {
			pilot.stop();
			//pilot.travel(50.0f);
			Thread.sleep(1000);
			pilot.rotate(90);
		}
	}
}