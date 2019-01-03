package happysolver.travelingsalesman.core;

import happysolver.travelingsalesman.api.CostFunction;
import happysolver.travelingsalesman.api.Vertex;

class EuclideanDistance implements CostFunction {

	@Override
	public double getCosts(Vertex v1, Vertex v2) {
		double powerX = Math.pow(((XYVertexImpl) v1).getX() - ((XYVertexImpl) v2).getX(), 2);
		double powerY = Math.pow(((XYVertexImpl) v1).getY() - ((XYVertexImpl) v2).getY(), 2);
		return Math.sqrt(powerX + powerY);
	}
}
