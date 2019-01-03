package travelingsalesman.api.core;

import java.util.List;

import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;

class TSPSolutionImpl implements TSPSolution {

	private final List<Vertex> vertexs;
	private final double value;

	public TSPSolutionImpl(List<Vertex> vertexs, double value) {
		this.vertexs = vertexs;
		this.value = value;
	}

	@Override
	public List<Vertex> getPath() {
		return vertexs;
	}

	@Override
	public double getValue() {
		return value;
	}

}
