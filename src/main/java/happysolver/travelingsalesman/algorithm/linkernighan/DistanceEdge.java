package happysolver.travelingsalesman.algorithm.linkernighan;

public class DistanceEdge implements Comparable<DistanceEdge> {

	private final int startIndex;
	private final int endIndex;
	private final double distance;

	public DistanceEdge(int startIndex, int endIndex, double distance) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.distance = distance;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public int compareTo(DistanceEdge o) {
		return Double.compare(distance, o.distance);
	}
}
