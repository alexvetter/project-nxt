package projectnxt.strategy;

import projectnxt.Roboter;

public interface Strategy extends Runnable {

	void setRoboter(Roboter roboter);

	Strategy getNextStrategy();
	
	void run();
}
