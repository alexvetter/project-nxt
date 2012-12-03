package projectnxt;

import projectnxt.strategy.Strategy;

public class StrategyFactory {

	private StrategyFactory() {
		// I'm a Factory
	}

	public static Strategy makePledgeMain() {
		return new projectnxt.strategy.pledge.Pledge();
	}
	
	public static Strategy makeQuadratMain() {
		return new projectnxt.strategy.quadrat.Quadrat();
	}
}
