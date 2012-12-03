package projectnxt.strategy.utils;

import projectnxt.Roboter;
import projectnxt.strategy.AbstractDoAndReturnStrategy;
import projectnxt.strategy.Strategy;

public class Travel extends AbstractDoAndReturnStrategy {
	Roboter bot;
	double distance;
	
	public Travel(Strategy parent, double distance) {
		super(parent);
		this.distance = distance;
	}

	public void setRoboter(Roboter roboter) {
		this.bot = roboter;
	}

	public void run() {
		bot.getPilot().travel(distance);
	}
}
