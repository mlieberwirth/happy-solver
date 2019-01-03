package travelingsalesman.algorithm.localsearch;

import common.Improver;
import common.StopCondition;
import common.localsearch.LocalSearchDirection;
import common.localsearch.LocalSearchExchangeHandler;
import common.localsearch.LocalSearchImprover;
import common.localsearch.LocalSearchIterator;
import travelingsalesman.api.CostFunction;
import travelingsalesman.api.TSPInput;
import travelingsalesman.api.TSPSolution;

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
