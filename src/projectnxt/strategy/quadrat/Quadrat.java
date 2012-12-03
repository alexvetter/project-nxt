package projectnxt.strategy.quadrat;

import projectnxt.Roboter;
import projectnxt.strategy.Strategy;
import projectnxt.strategy.utils.Turn;

public class Quadrat implements Strategy {

	private Roboter bot;
	
	public void setRoboter(Roboter roboter) {
		this.bot = roboter;
		
		roboter.getPilot().setTravelSpeed(30);
		roboter.getPilot().setRotateSpeed(50);
	}

	public Strategy getNextStrategy() {
		return new Turn(this, 90.0);
	}

	public void run() {
		bot.getPilot().travel(50.0f);
	}
}
