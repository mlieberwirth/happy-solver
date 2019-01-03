package travelingsalesman.algorithm.linkernighan;

import java.util.List;

import common.Improver;
import common.StopCondition;
import travelingsalesman.api.CostFunction;
import travelingsalesman.api.TSPInput;
import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;
import travelingsalesman.core.TSPFactory;

public class LinKernighan implements Improver<TSPInput, TSPSolution> {

	private final int UNUSED = -1;

	private double[][] distanceMatrix;
	private SolutionInfo solutionInfo;
	private NeighbourLists neighbour;
	private double G_i;

	// Arrays to save the best solution till now
	private SolutionInfo bestSolutionInfo;
	private int connectToT1;

	// arrays to save the config at entry into main loop
	// saved for backtracking
	private SolutionInfo backupSolutionInfo;
	private DistanceEdge backupY;
	private double backupG_i;

	private SolutionInfo firstBackupSolutionInfo;

	private int numberVertices;

	private boolean backtrack = true;

	@Override
	public TSPSolution improve(TSPInput input, TSPSolution startSolution) {

		initFields(input, startSolution);
		runOneIteration();
		return createSolution();
	}

	@Override
	public TSPSolution improve(TSPInput input, TSPSolution startSolution, StopCondition stopCondition) {

		initFields(input, startSolution);

		for (int iteration = 0; iteration < 10; iteration++) {
			double oldValue = solutionInfo.calcCurrentTourLength(distanceMatrix);
			runOneIteration();
			double value = solutionInfo.calcCurrentTourLength(distanceMatrix);
			if (stopCondition.doTerminate(oldValue, value, iteration)) {
				break;
			}
		}

		return createSolution();
	}

	private void initFields(TSPInput input, TSPSolution startSolution) {
		CostFunction costFunction = input.getCostFunction();
		List<Vertex> vertexList = input.getVertexList();

		numberVertices = vertexList.size();
		distanceMatrix = new CreateCostMatrix().create(costFunction, vertexList);
		neighbour = new NeighbourLists(distanceMatrix, vertexList);
		solutionInfo = new SolutionInfo(startSolution, vertexList);
	}

	private TSPSolution createSolution() {
		double tourLength = solutionInfo.calcCurrentTourLength(distanceMatrix);
		List<Vertex> result = solutionInfo.getCurrentTour();
		return new TSPFactory().createSolution(result, tourLength);
	}

	private void rememberSolution() {
		bestSolutionInfo = solutionInfo.clone();
	}

	private void restoreSolution() {
		solutionInfo = bestSolutionInfo.clone();
	}

	private void createBackup(DistanceEdge y) {
		backupSolutionInfo = solutionInfo.clone();
		backupG_i = G_i;
		backupY = y;
	}

	private DistanceEdge restoreBackup() {
		solutionInfo = backupSolutionInfo.clone();
		G_i = backupG_i;
		return backupY;
	}

	private void createFirstBackup() {
		firstBackupSolutionInfo = solutionInfo.clone();
	}

	private void restoreFirstBackup() {
		solutionInfo = firstBackupSolutionInfo.clone();
	}

	private boolean isInTour(DistanceEdge e) {
		return solutionInfo.getPred(e.getStartIndex()) == e.getEndIndex()
				|| solutionInfo.getPred(e.getEndIndex()) == e.getStartIndex();
	}

	private void runOneIteration() {
		double bestLength = solutionInfo.calcCurrentTourLength(distanceMatrix);
		for (int i = 0; i < numberVertices; i++) {
			solutionInfo.cleanBlocked();
			boolean improvement = true;
			while (improvement) {
				improvement = false;
				connectToT1 = solutionInfo.getPred(i);
				runOneIteration(i);
				solutionInfo.setSucc(connectToT1, i);
				solutionInfo.setPred(i, connectToT1);
				double currentTourLength = solutionInfo.calcCurrentTourLength(distanceMatrix);
				if (bestLength > currentTourLength) {
					bestLength = currentTourLength;
					improvement = true;
				}
				SolutionCheck.checkTour(solutionInfo);
			}
		}
	}

	/**
	 * executes the Lin-Kernighan algorithm for a given starting point	 *
	 */
	private void runOneIteration(int start) {
		double Gbest = 0; // Saves the best change till now
		G_i = 0; // Saves the actual gain at iteration i
		int i;
		neighbour.reset();

		// Arrays to save the best solution till now
		rememberSolution();

		int t1 = start;
		int _t1 = UNUSED;
		int _t2 = UNUSED;
		int t2 = UNUSED;

		// choose t2
		// if Gbest is 0 after the first iteration, choose the again (6d)
		for (int j = 0; j <= 1 && Gbest == 0; j++) {
			i = 1;

			if (j == 1) {
				restoreSolution();
			}

			// first try the longer edge and I this fails, the shorter
			if ((j == 0 && distanceMatrix[t1][solutionInfo.getPred(t1)] >= distanceMatrix[t1][solutionInfo.getSucc(t1)])
					|| (j == 1 && distanceMatrix[t1][solutionInfo.getPred(t1)] < distanceMatrix[t1][solutionInfo
							.getSucc(t1)])) {
				t2 = solutionInfo.getPred(t1);

				// break the connection between t1 & t2
				solutionInfo.setPred(t1, UNUSED);
				solutionInfo.setSucc(t2, UNUSED);
			} else {
				t2 = solutionInfo.getSucc(t1);

				// break the connection between t1 & t2
				solutionInfo.setPred(t2, UNUSED);
				solutionInfo.setSucc(t1, UNUSED);

				// reverse the sense of the entire circle
				solutionInfo.reverseAll();
			}

			SolutionCheck.checkClosable(solutionInfo, t1);

			// Block and remember x1
			double distance = distanceMatrix[t1][t2];
			DistanceEdge x = new DistanceEdge(t1, t2, distance);

			if (solutionInfo.isBlocked(x.getStartIndex(), x.getEndIndex())) {
				continue;
			}

			solutionInfo.block(x.getStartIndex(), x.getEndIndex());

			neighbour.makeNeighbour(t2);

			createFirstBackup();
			
			// search y1
			// to privide limited backtracking, select candiates for y1
			// in increasing length
			while (Gbest == 0 && neighbour.hasMoreNeighbours(t2)) {
				restoreFirstBackup();
				DistanceEdge y = neighbour.getNextNeighbour(t2);

				// Check if the choosen edge is already in the tour
				// If it is so, select another y
				if (isInTour(y)) {
					continue;
				}

				if (solutionInfo.isBlocked(y.getStartIndex(), y.getEndIndex())) {
					continue;
				}

				// check the gain criterion
				// if it fails once it will always fail
				// because all the other choices of y will be even longet
				G_i = x.getDistance() - y.getDistance();
				if (G_i < 0) {
					G_i = 0;
					break; // if the gain criterion failed, select a new y1
				}
				int t3;

				// remember the old pred of _t1 (endpoint of y)
				t3 = y.getEndIndex();

				int old_pred_t1 = solutionInfo.getPred(t3);

				// insert and block the new Edge
				// direction of y1 is always back
				solutionInfo.insertLink(y, t2);
				solutionInfo.block(y);

				createBackup(y);
				backtrack = true;
				while (Gbest == 0 && backtrack == true) {
					_t2 = t2;
					_t1 = t3;
					y = restoreBackup();
					i = 1;
					do {
						i++;
						// get xi
						// t2i-1 will be called _t1
						// t2i will be called _t2
						// like this we have the same names as for the first x1
						// get and break next x, where t1 is the starting point
						_t2 = solutionInfo.breakLink(_t1);

						// If x is already blocked, restore the last solution
						// and leave the loop for backtracking
						if (solutionInfo.isBlocked(_t1, _t2)) {
							restoreBackup();
							if (i == 2) {
								backtrack = false;
							}
							break;
						}
						solutionInfo.block(_t1, _t2);

						// now we have to reverse the orientation from t2_old
						// backwards
						solutionInfo.reverseAfter(_t1);

						// set the pred of t1 to the old pred (we lost this
						// info before)
						solutionInfo.setPred(_t1, old_pred_t1);

						SolutionCheck.checkClosable(solutionInfo, t1);

						// now check whether closing the up would lead us to a
						// better tour than
						// the best tour seen before
						double g = distanceMatrix[_t2][_t1] - distanceMatrix[_t2][t1];
						if (G_i + g > Gbest) {
							Gbest = G_i + g;
							rememberSolution();
							connectToT1 = _t2;
						}

						neighbour.makeNeighbour(_t2);
						// get the next y, if possible
						if (!neighbour.hasMoreNeighbours(_t2)) {
							break;
						}
						do {
							y = neighbour.getNextNeighbour(_t2);
							if (!solutionInfo.isBlocked(y.getStartIndex(), y.getEndIndex())) {
								if (!isInTour(y)) {
									break;
								}
							}
						} while (neighbour.hasMoreNeighbours(_t2));

						int _t1_old = _t1;
						_t1 = y.getEndIndex();
						old_pred_t1 = solutionInfo.getPred(_t1);

						// insert and block the new Edge
						solutionInfo.insertLink(y, _t2);
						solutionInfo.block(y);

						G_i += distanceMatrix[_t2][_t1_old] - y.getDistance();

						// tour complete
						if (y.getStartIndex() == t1 || y.getEndIndex() == t1) {
							break;
						}

					} while (G_i >= Gbest);
					if (Gbest == 0) {
						restoreBackup();
					}
				}
				// we left the loop, so we have to restore the best solution
				// found!
				if (Gbest > 0) {
					restoreSolution();
				}
			}
			restoreSolution();
		}
		restoreSolution();
	}

}
