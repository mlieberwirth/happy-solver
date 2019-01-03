package travelingsalesman.api.core;

import travelingsalesman.api.Vertex;

class XYVertexImpl implements Vertex {

	private final String id;
	private final double x;
	private final double y;

	private int index;

	public XYVertexImpl(String id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	@Override
	public String getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return getId();
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}
}
