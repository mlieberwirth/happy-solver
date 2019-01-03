package happysolver.travelingsalesman.algorithm.linkernighan;

import java.util.ArrayList;
import java.util.List;

import happysolver.travelingsalesman.api.TSPSolution;
import happysolver.travelingsalesman.api.Vertex;

public class SolutionInfo implements Cloneable {

	private static final int UNUSED = -1;

	private int[] succVertex;
	private int[] predVertex;
	private boolean[] reversed;
	private boolean[][] blocked;
	private final int numberVertices;
	private Vertex[] vertexArray;

	public SolutionInfo(TSPSolution tspSolution, List<Vertex> vertexList) {
		this.numberVertices = vertexList.size();
		vertexArray = new Vertex[getNumberVertices()];
		for (int k = 0; k < vertexList.size(); k++) {
			vertexArray[k] = vertexList.get(k);
		}

		succVertex = new int[getNumberVertices()];
		predVertex = new int[getNumberVertices()];
		reversed = new boolean[getNumberVertices()];
		blocked = new boolean[getNumberVertices()][getNumberVertices()];
		
		setStartTour(tspSolution);
	}

	private void setStartTour(TSPSolution tspSolution) {
		List<Vertex> path = tspSolution.getPath();
		int next = path.get(0).getIndex();
		int actual = path.get(0).getIndex();
		for (int i = 1; i < getNumberVertices(); i++) {
			next = path.get(i).getIndex();
			succVertex[actual] = next;
			predVertex[next] = actual;
			actual = next;
		}
		succVertex[actual] = path.get(0).getIndex();
		predVertex[path.get(0).getIndex()] = actual;
	}

	@Override
	public SolutionInfo clone() {
		try {
			SolutionInfo clone = (SolutionInfo) super.clone();
			clone.succVertex = succVertex.clone();
			clone.predVertex = predVertex.clone();
			clone.reversed = reversed.clone();
			clone.blocked = blocked.clone();
			// vertexArray not necessary, is final
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isReversed(int index) {
		return reversed[index];
	}

	public boolean getReversed(int i) {
		return reversed[i];
	}

	public void setReversed(int index, boolean value) {
		reversed[index] = value;
	}

	public void cleanBlocked() {
		for (int i = 0; i < getNumberVertices(); i++) {
			for (int j = 0; j < getNumberVertices(); j++) {
				blocked[i][j] = false;
			}
		}
	}

	public int getSucc(int i) {
		if (!reversed[i]) {
			return succVertex[i];
		}
		return predVertex[i];
	}

	public void setSucc(int node, int i) {
		if (reversed[node]) {
			predVertex[node] = i;
		} else {
			succVertex[node] = i;
		}
	}

	public int getPred(int i) {
		if (!reversed[i]) {
			return predVertex[i];
		}
		return succVertex[i];
	}

	public void setPred(int node, int i) {
		if (!reversed[node]) {
			predVertex[node] = i;
		} else {
			succVertex[node] = i;
		}
	}

	public void reverseAfter(int i) {
		while (i != UNUSED) {
			reversed[i] = !reversed[i];
			// normally we would go the the predator of i
			// but as we swaped direction we go the other way now
			i = getSucc(i);
		}
	}

	public void reverseAll() {
		for (int i = 0; i < getNumberVertices(); i++) {
			reversed[i] = !reversed[i];
		}
	}

	public void block(DistanceEdge e) {
		block(e.getStartIndex(), e.getEndIndex());
	}

	public void block(int start, int end) {
		blocked[start][end] = true;
		blocked[end][start] = true;
	}

	public boolean isBlocked(int start, int end) {
		return blocked[start][end];
	}

	public List<Vertex> getCurrentTour() {
		List<Vertex> result = new ArrayList<>();

		int next = getSucc(0);
		result.add(vertexArray[next]);
		for (int i = 1; i < getNumberVertices(); i++) {
			next = getSucc(next);
			result.add(vertexArray[next]);
		}
		return result;
	}

	public double calcCurrentTourLength(double[][] distanceMatrix) {
		double result = 0;
		for (int i = 0; i < getNumberVertices(); i++) {
			int j = getSucc(i);
			if (j != UNUSED) {
				result += distanceMatrix[i][j];
			}
		}
		return result;
	}

	public void insertLink(DistanceEdge e, int start) {
		int end;

		if (e.getStartIndex() == start) {
			end = e.getEndIndex();
		} else {
			end = e.getStartIndex();
		}

		setSucc(start, end);
		setPred(end, start);
	}

	public int breakLink(int start) {
		int end = getSucc(start);

		setSucc(start, UNUSED);
		setPred(end, UNUSED);

		return end;
	}

	public int getNumberVertices() {
		return numberVertices;
	}
}
