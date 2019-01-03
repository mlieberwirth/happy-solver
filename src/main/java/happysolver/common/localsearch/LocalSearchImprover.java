package happysolver.common.localsearch;

import happysolver.common.Improver;
import happysolver.common.Input;
import happysolver.common.Solution;
import happysolver.common.StopCondition;

public class LocalSearchImprover<I extends Input, S extends Solution, E extends LocalSearchExchange>
		implements Improver<I, S> {

	private final LocalSearchIterator<S, E> iterator;
	private final LocalSearchExchangeHandler<S, E> exchangeHandler;
	private final LocalSearchDirection localSearchDirection;

	public LocalSearchImprover(LocalSearchIterator<S, E> iterator, LocalSearchExchangeHandler<S, E> exchangeHandler,
			LocalSearchDirection localSearchDirection) {
		this.iterator = iterator;
		this.exchangeHandler = exchangeHandler;
		this.localSearchDirection = localSearchDirection;
	}

	@Override
	public S improve(I input, S startSolution) {
		iterator.init(startSolution);
		S currentSolution = startSolution;
		while (iterator.hasNext()) {
			E exchange = iterator.next();
			double newValue = exchangeHandler.calculateNewValue(currentSolution, exchange);
			if (localSearchDirection.isImprovement(currentSolution.getValue(), newValue)) {
				currentSolution = exchangeHandler.doExchange(currentSolution, exchange);
				iterator.update(currentSolution, exchange);
			}
		}
		return currentSolution;
	}

	@Override
	public S improve(I input, S startSolution, StopCondition stopCondition) {
		double oldValue;
		S currentSolution = startSolution;
		int iteration = 0;
		do {
			oldValue = currentSolution.getValue();
			S newSolution = improve(input, currentSolution);
			if (localSearchDirection.isImprovement(oldValue, newSolution.getValue())) {
				currentSolution = newSolution;
			}
			iteration++;
		} while (!stopCondition.doTerminate(oldValue, currentSolution.getValue(), iteration));
		return currentSolution;
	}
}
