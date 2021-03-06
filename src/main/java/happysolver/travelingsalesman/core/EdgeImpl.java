package happysolver.travelingsalesman.core;

import happysolver.travelingsalesman.api.Edge;
import happysolver.travelingsalesman.api.Vertex;

class EdgeImpl implements Edge {

	private final Vertex start;
	private final Vertex end;

	public EdgeImpl(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Vertex getStart() {
		return start;
	}

	@Override
	public Vertex getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return start + " -> " + end;
	}
}
