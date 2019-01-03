package travelingsalesman.algorithm.linkernighan;

import java.util.List;

import travelingsalesman.api.CostFunction;
import travelingsalesman.api.Vertex;

public class CreateCostMatrix {

	public double[][] create(CostFunction costFunction, List<Vertex> liste) {
		int listeLength = liste.size();
		double[][] result = new double[listeLength][listeLength];
		for (int i = 0; i < liste.size() - 1; i++) {
			Vertex vertex1 = liste.get(i);
			for (int j = i + 1; j < liste.size(); j++) {
				Vertex vertex2 = liste.get(j);
				double dis = costFunction.getCosts(vertex1, vertex2);
				result[vertex1.getIndex()][vertex2.getIndex()] = result[vertex2.getIndex()][vertex1.getIndex()] = dis;
			}
		}
		return result;
	}
}
