package projectnxt.strategy.utils;

import projectnxt.Roboter;
import projectnxt.strategy.AbstractDoAndReturnStrategy;
import projectnxt.strategy.Strategy;

public class Turn extends AbstractDoAndReturnStrategy {
	Roboter bot;
	double angle;
	
	public Turn(Strategy parent, double angle) {
		super(parent);
		this.angle = angle;
	}

	public void setRoboter(Roboter roboter) {
		this.bot = roboter;
	}

	public void run() {
		bot.getPilot().rotate(angle);
	}
}
