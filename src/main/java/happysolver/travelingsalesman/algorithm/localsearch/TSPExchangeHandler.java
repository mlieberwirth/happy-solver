package happysolver.travelingsalesman.algorithm.localsearch;

import java.util.ArrayList;
import java.util.List;

import happysolver.common.localsearch.LocalSearchExchangeHandler;
import happysolver.travelingsalesman.api.CostFunction;
import happysolver.travelingsalesman.api.Edge;
import happysolver.travelingsalesman.api.TSPSolution;
import happysolver.travelingsalesman.api.Vertex;
import happysolver.travelingsalesman.core.TSPFactory;

public class TSPExchangeHandler implements LocalSearchExchangeHandler<TSPSolution, TSPExchange> {

	private final TSPFactory factory = new TSPFactory();
	private final CostFunction costFunction;

	public TSPExchangeHandler(CostFunction costFunction) {
		this.costFunction = costFunction;
	}

	@Override
	public TSPSolution doExchange(TSPSolution solution, TSPExchange exchange) {
		List<Vertex> oldPath = solution.getPath();

		int startIndex = oldPath.indexOf(exchange.getOldEdge1().getStart());
		List<Vertex> newPath = new ArrayList<>(oldPath.subList(0, startIndex + 1));

		int endIndex = oldPath.indexOf(exchange.getOldEdge2().getStart());
		List<Vertex> subList = oldPath.subList(startIndex + 1, endIndex+1);
		subList = invertOrder(subList);

		newPath.addAll(subList);

		newPath.addAll(oldPath.subList(endIndex+1, oldPath.size()));

		double newValue = calculateNewValue(solution, exchange);

		return factory.createSolution(newPath, newValue);
	}

	private List<Vertex> invertOrder(List<Vertex> list) {
		List<Vertex> result = new ArrayList<>();

		for (Vertex vertex : list) {
			result.add(0, vertex);
		}
		return result;
	}

	@Override
	public double calculateNewValue(TSPSolution solution, TSPExchange exchange) {
		double lose = getCosts(exchange.getOldEdge1()) + getCosts(exchange.getOldEdge2());
		double add = getCosts(exchange.getNewEdge1()) + getCosts(exchange.getNewEdge2());
		return solution.getValue() - lose + add;
	}

	private double getCosts(Edge edge) {
		return costFunction.getCosts(edge.getStart(), edge.getEnd());
	}
}
