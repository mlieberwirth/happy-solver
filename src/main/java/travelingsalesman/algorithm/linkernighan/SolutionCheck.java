package travelingsalesman.algorithm.linkernighan;

public class SolutionCheck {

	private static final int UNUSED = -1;

	public static boolean checkTour(SolutionInfo solutionInfo) {
		int numberVertices = solutionInfo.getNumberVertices();

		int posPred = 0; // starting position
		int posSucc = 0;
		boolean[] touchedPred = new boolean[numberVertices];
		boolean[] touchedSucc = new boolean[numberVertices];

		for (int i = 0; i < numberVertices; i++) {
			touchedPred[i] = touchedSucc[i] = false;
		}

		// check whether after l steps you are at the begin again
		// and check whether we have touched all nodes
		// do the check in both directions at the same time
		for (int i = 0; i < numberVertices; i++) {
			posSucc = solutionInfo.getSucc(posSucc);
			posPred = solutionInfo.getPred(posPred);

			if (posSucc == UNUSED) // there is a break!
			{
				System.out.println("A Node has no succ: " + posSucc);
				return false;
			} else if (posPred == UNUSED) {
				System.out.println("A Node has no pred: " + posPred);
				return false;
			}

			// mark node true
			touchedPred[posPred] = true;
			touchedSucc[posSucc] = true;

		}
		if (posSucc != 0 || posPred != 0) // we are not a the begin again
		{
			System.out.println("This is not a cycle!");
			if (posSucc != 0 && posPred != 0) {
				System.out.println("In both direcctions");
			}
			if (posPred != 0) {
				System.out.println("In pred direcction");
			}
			if (posSucc != 0) {
				System.out.println("In succ direcction");
			}

			return false;
		}

		// check if all nodes were touched
		for (int i = 0; i < numberVertices; i++) {
			if (!touchedSucc[i] || !touchedPred[i]) {
				System.out.println("Node" + i + "was not touched!!!");
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if we have a path trough all the nodes If we do so, the path can
	 * be closed to a complete cycle
	 *
	 * @param start
	 *            startnode of path
	 * @return true, if it can be closed, false else
	 */
	public static boolean checkClosable(SolutionInfo solutionInfo, int start) {
		int numberVertices = solutionInfo.getNumberVertices();
		boolean[] touched = new boolean[numberVertices];

		// initialise touched with false as no node has been touched before
		for (int i = 0; i < numberVertices; i++) {
			touched[i] = false;
		}

		touched[start] = true;
		int pos = solutionInfo.getSucc(start);

		// walk till you reach a node where you have been are at the end
		while (pos != UNUSED && !touched[pos]) {
			touched[pos] = true;
			pos = solutionInfo.getSucc(pos);
		}

		if (pos == UNUSED) // we found an end
		{
			// check whether all nodes were touched, because it is so,
			// then we can link pos to start and have a tour
			for (int i = 1; i < numberVertices; i++) {
				if (!touched[i]) {
					System.out.println("Node " + i + "was not touched");
					return false;
				}
			}
		} else // we have a cycle
		{
			System.out.println("We have a cycle starting in " + pos);
			return false;
		}

		return true;
	}

}
