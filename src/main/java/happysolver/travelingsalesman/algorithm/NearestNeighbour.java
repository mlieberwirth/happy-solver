package happysolver.travelingsalesman.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import happysolver.common.RandomOptimization;
import happysolver.travelingsalesman.api.CostFunction;
import happysolver.travelingsalesman.api.TSPInput;
import happysolver.travelingsalesman.api.TSPSolution;
import happysolver.travelingsalesman.api.Vertex;
import happysolver.travelingsalesman.core.TSPFactory;

public class NearestNeighbour implements RandomOptimization<TSPInput, TSPSolution> {

	private final TSPFactory factory = new TSPFactory();
	private final Random random = new Random(0);

	@Override
	public List<TSPSolution> run(TSPInput input, int numberRuns) {
		List<Vertex> startVertexList = new ArrayList<>(input.getVertexList());
		List<TSPSolution> solutions = new ArrayList<>();
		for (int i = 0; i < numberRuns; i++) {
			int startIndex = random.nextInt(startVertexList.size());
			Vertex startVertex = startVertexList.remove(startIndex);
			TSPSolution solution = run(input.getVertexList(), startVertex, input.getCostFunction());
			solutions.add(solution);
		}
		return solutions;
	}

	@Override
	public TSPSolution run(TSPInput input) {
		return run(input.getVertexList(), input.getVertexList().get(0), input.getCostFunction());
	}

	private TSPSolution run(Collection<Vertex> vertexes, Vertex startVertex, CostFunction costFunction) {

		List<Vertex> solutionPath = new ArrayList<>();
		Set<Vertex> notChosenVertexSet = new LinkedHashSet<>(vertexes);

		Vertex currentVertex = startVertex;
		notChosenVertexSet.remove(currentVertex);
		solutionPath.add(currentVertex);

		double costSum = 0;

		while (!notChosenVertexSet.isEmpty()) {
			VertexWithCosts vertexWithCosts = findNearestVertex(currentVertex, costFunction, notChosenVertexSet);
			currentVertex = vertexWithCosts.getVertex();
			costSum += vertexWithCosts.getCosts();
			solutionPath.add(currentVertex);
			notChosenVertexSet.remove(currentVertex);
		}

		costSum += costFunction.getCosts(currentVertex, startVertex);
		solutionPath.add(startVertex);

		return factory.createSolution(solutionPath, costSum);
	}

	private VertexWithCosts findNearestVertex(Vertex currentVertex, CostFunction costFunction,
			Set<Vertex> notChosenVertexSet) {
		double min = Double.MAX_VALUE;
		Vertex nearestVertex = null;
		for (Vertex tmpVertex : notChosenVertexSet) {
			double costs = costFunction.getCosts(currentVertex, tmpVertex);
			if (costs < min) {
				min = costs;
				nearestVertex = tmpVertex;
			}
		}
		return new VertexWithCosts(min, nearestVertex);
	}

	private static class VertexWithCosts {

		private final double costs;
		private final Vertex vertex;

		public VertexWithCosts(double costs, Vertex vertex) {
			this.costs = costs;
			this.vertex = vertex;
		}

		public double getCosts() {
			return costs;
		}

		public Vertex getVertex() {
			return vertex;
		}
	}
}
