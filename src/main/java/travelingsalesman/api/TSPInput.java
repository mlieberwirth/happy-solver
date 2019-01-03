package travelingsalesman.api;

import java.util.List;

import common.Input;

public interface TSPInput extends Input {

	CostFunction getCostFunction();

	List<Vertex> getVertexList();
}
