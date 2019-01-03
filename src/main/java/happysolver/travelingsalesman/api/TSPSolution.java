package happysolver.travelingsalesman.api;

import java.util.List;

import happysolver.common.Solution;

public interface TSPSolution extends Solution {

	List<Vertex> getPath();
}
