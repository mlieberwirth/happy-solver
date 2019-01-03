package travelingsalesman.api.core;

import travelingsalesman.api.CostFunction;
import travelingsalesman.api.Vertex;

class EuclideanDistanceRound implements CostFunction {

	@Override
	public double getCosts(Vertex v1, Vertex v2) {
		double powerX = Math.pow(((XYVertexImpl) v1).getX() - ((XYVertexImpl) v2).getX(), 2);
		double powerY = Math.pow(((XYVertexImpl) v1).getY() - ((XYVertexImpl) v2).getY(), 2);
		return Math.round(Math.sqrt(powerX + powerY));
	}
}
