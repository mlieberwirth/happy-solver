package travelingsalesman.algorithm.linkernighan;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import travelingsalesman.api.Vertex;

public class NeighbourLists {

	private final List<DistanceEdge>[] nextNeighbourArray;
	private final int[] indexArray;
	private final List<Vertex> vertexList;
	private final double[][] distanceMatrix;

	@SuppressWarnings("unchecked")
	public NeighbourLists(double[][] distanceMatrix, List<Vertex> vertexList) {
		this.distanceMatrix = distanceMatrix;
		this.vertexList = vertexList;
		nextNeighbourArray = new List[vertexList.size()];
		indexArray = new int[vertexList.size()];
	}

	public void makeNeighbour(int vertexIndex) {
		if (nextNeighbourArray[vertexIndex] != null) {
			return;
		}

		nextNeighbourArray[vertexIndex] = new ArrayList<>();
		PriorityQueue<DistanceEdge> priorityQueue = new PriorityQueue<>(indexArray.length);

		for (int j = 0; j < vertexList.size(); j++) {
			if (vertexIndex != j) {
				double distance = distanceMatrix[vertexIndex][j];
				DistanceEdge distanceEdge = new DistanceEdge(vertexIndex, j, distance);
				priorityQueue.add(distanceEdge);
			}
		}

		while (!priorityQueue.isEmpty()) {
			nextNeighbourArray[vertexIndex].add(priorityQueue.poll());
		}
	}

	public void reset() {
		for (int k = 0; k < indexArray.length; k++) {
			indexArray[k] = 0;
		}
	}

	public DistanceEdge getNextNeighbour(int vertexIndex) {
		int currentIndex = indexArray[vertexIndex];
		indexArray[vertexIndex]++;
		return nextNeighbourArray[vertexIndex].get(currentIndex);
	}

	public boolean hasMoreNeighbours(int vertexIndex) {
		int currentIndex = indexArray[vertexIndex];
		return currentIndex < nextNeighbourArray[vertexIndex].size();
	}
}
