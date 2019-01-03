package happysolver.travelingsalesman.core;

import java.util.List;

import happysolver.travelingsalesman.api.CostFunction;
import happysolver.travelingsalesman.api.TSPInput;
import happysolver.travelingsalesman.api.Vertex;

class TSPInputImpl implements TSPInput {

	private final CostFunction costFunction;
	private final List<Vertex> vertexs;

	public TSPInputImpl(CostFunction costFunction, List<Vertex> vertexs) {
		this.costFunction = costFunction;
		this.vertexs = vertexs;
	}

	@Override
	public CostFunction getCostFunction() {
		return costFunction;
	}

	@Override
	public List<Vertex> getVertexList() {
		return vertexs;
	}

}
