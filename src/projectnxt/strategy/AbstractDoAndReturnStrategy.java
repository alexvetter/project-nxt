package projectnxt.strategy;

import projectnxt.Roboter;

public abstract class AbstractDoAndReturnStrategy implements Strategy {
	Strategy parent;
	
	public AbstractDoAndReturnStrategy(Strategy parent) {
		this.parent = parent;
	}

	public abstract void setRoboter(Roboter roboter);

	public final Strategy getNextStrategy() {
		return parent;
	}

	public abstract void run();
}
