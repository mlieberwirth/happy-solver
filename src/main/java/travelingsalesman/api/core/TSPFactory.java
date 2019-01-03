package travelingsalesman.api.core;

import java.util.List;

import travelingsalesman.api.CostFunction;
import travelingsalesman.api.Edge;
import travelingsalesman.api.TSPInput;
import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;

public class TSPFactory {

	public Vertex createVertex(String id, double x, double y) {
		return new XYVertexImpl(id, x, y);
	}

	public Edge createEdge(Vertex start, Vertex end) {
		return new EdgeImpl(start, end);
	}

	public CostFunction createEuclideanDistance() {
		return new EuclideanDistance();
	}

	public CostFunction createEuclideanDistanceRound() {
		return new EuclideanDistanceRound();
	}

	public TSPInput createInput(CostFunction costFunction, List<Vertex> vertexs) {
		return new TSPInputImpl(costFunction, vertexs);
	}

	public TSPSolution createSolution(List<Vertex> vertexs, double value) {
		return new TSPSolutionImpl(vertexs, value);
	}
}
