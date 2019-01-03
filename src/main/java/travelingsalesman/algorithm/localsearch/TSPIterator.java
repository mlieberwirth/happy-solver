package travelingsalesman.algorithm.localsearch;

import java.util.ArrayList;
import java.util.List;

import common.localsearch.LocalSearchIterator;
import travelingsalesman.api.Edge;
import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;
import travelingsalesman.core.TSPFactory;

public class TSPIterator implements LocalSearchIterator<TSPSolution, TSPExchange> {

	private final TSPFactory factory = new TSPFactory();
	private final List<TSPExchange> exchangeList = new ArrayList<>();
	private int index;

	@Override
	public boolean hasNext() {
		return index < exchangeList.size();
	}

	@Override
	public TSPExchange next() {
		TSPExchange exchange = exchangeList.get(index);
		index++;
		return exchange;
	}

	@Override
	public void init(TSPSolution solution) {
		exchangeList.clear();
		index = 0;
		List<Vertex> path = new ArrayList<>(solution.getPath());
		for (int i = 0; i < path.size() - 3; i++) {
			Vertex vertex11 = path.get(i);
			Vertex vertex12 = path.get(i + 1);
			Edge oldEdge1 = factory.createEdge(vertex11, vertex12);
			for (int j = i + 2; j < path.size() - 1; j++) {
				Vertex vertex21 = path.get(j);
				Vertex vertex22 = path.get(j + 1);
				if (vertex22 == vertex11) {
					break;
				}
				Edge oldEdge2 = factory.createEdge(vertex21, vertex22);
				Edge newEdge1 = factory.createEdge(vertex11, vertex21);
				Edge newEdge2 = factory.createEdge(vertex22, vertex12);
				TSPExchange exchange = new TSPExchange(oldEdge1, oldEdge2, newEdge1, newEdge2);
				exchangeList.add(exchange);
			}
		}
	}

	@Override
	public void update(TSPSolution newsolution, TSPExchange currentExchange) {
		int oldIndex = index;
		init(newsolution);
		index = oldIndex;
	}
}
