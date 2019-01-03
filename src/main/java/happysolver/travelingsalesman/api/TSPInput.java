package happysolver.travelingsalesman.api;

import java.util.List;

import happysolver.common.Input;

public interface TSPInput extends Input {

	CostFunction getCostFunction();

	List<Vertex> getVertexList();
}
