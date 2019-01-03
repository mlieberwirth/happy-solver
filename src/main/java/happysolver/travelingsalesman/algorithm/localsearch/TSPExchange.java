package happysolver.travelingsalesman.algorithm.localsearch;

import happysolver.common.localsearch.LocalSearchExchange;
import happysolver.travelingsalesman.api.Edge;

public class TSPExchange implements LocalSearchExchange {

	private final Edge oldEdge1;
	private final Edge oldEdge2;

	private final Edge newEdge1;
	private final Edge newEdge2;

	public TSPExchange(Edge oldEdge1, Edge oldEdge2, Edge newEdge1, Edge newEdge2) {
		this.oldEdge1 = oldEdge1;
		this.oldEdge2 = oldEdge2;
		this.newEdge1 = newEdge1;
		this.newEdge2 = newEdge2;
	}

	public Edge getOldEdge1() {
		return oldEdge1;
	}

	public Edge getOldEdge2() {
		return oldEdge2;
	}

	public Edge getNewEdge1() {
		return newEdge1;
	}

	public Edge getNewEdge2() {
		return newEdge2;
	}
}
