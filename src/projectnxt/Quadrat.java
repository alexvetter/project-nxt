package projectnxt;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Quadrat {
	public static void main(String[] args) {
		final DifferentialPilot pilot = new DifferentialPilot(2.4f, 14.5f, Motor.A, Motor.B, false);
		
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

		LCD.drawString("Ready...", 0, 0);
		Button.ENTER.waitForPress();
		
		
		pilot.setRotateSpeed(500);
		pilot.forward();
		
		while (true) {
			pilot.stop();
			pilot.travel(20);
			
			pilot.rotate(90);
		}
		
	} // main

}