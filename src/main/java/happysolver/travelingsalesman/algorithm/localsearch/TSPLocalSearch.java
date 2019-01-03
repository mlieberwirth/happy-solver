package happysolver.travelingsalesman.algorithm.localsearch;

import happysolver.common.Improver;
import happysolver.common.StopCondition;
import happysolver.common.localsearch.LocalSearchDirection;
import happysolver.common.localsearch.LocalSearchExchangeHandler;
import happysolver.common.localsearch.LocalSearchImprover;
import happysolver.common.localsearch.LocalSearchIterator;
import happysolver.travelingsalesman.api.CostFunction;
import happysolver.travelingsalesman.api.TSPInput;
import happysolver.travelingsalesman.api.TSPSolution;

public class TSPLocalSearch implements Improver<TSPInput, TSPSolution> {

	@Override
	public TSPSolution improve(TSPInput input, TSPSolution startSolution) {
		LocalSearchImprover<TSPInput, TSPSolution, TSPExchange> localSearch = createLocalSearch(
				input.getCostFunction());
		return localSearch.improve(input, startSolution);
	}

	@Override
	public TSPSolution improve(TSPInput input, TSPSolution startSolution, StopCondition stopCondition) {
		LocalSearchImprover<TSPInput, TSPSolution, TSPExchange> localSearch = createLocalSearch(
				input.getCostFunction());
		return localSearch.improve(input, startSolution, stopCondition);
	}

	private LocalSearchImprover<TSPInput, TSPSolution, TSPExchange> createLocalSearch(CostFunction costFunction) {
		LocalSearchIterator<TSPSolution, TSPExchange> iterator = new TSPIterator();
		LocalSearchExchangeHandler<TSPSolution, TSPExchange> exchangeHandler = new TSPExchangeHandler(costFunction);
		LocalSearchDirection localSearchDirection = LocalSearchDirection.DECREASE;
		return new LocalSearchImprover<>(iterator, exchangeHandler, localSearchDirection);
	}
}
