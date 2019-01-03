package travelingsalesman.api;

import java.util.List;

import common.Solution;

public interface TSPSolution extends Solution {

	List<Vertex> getPath();
}
