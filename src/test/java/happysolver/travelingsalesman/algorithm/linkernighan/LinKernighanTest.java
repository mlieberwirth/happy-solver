package happysolver.travelingsalesman.algorithm.linkernighan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

import org.junit.Test;

import happysolver.common.StopCondition;
import happysolver.travelingsalesman.algorithm.NearestNeighbour;
import happysolver.travelingsalesman.algorithm.TSPParser;
import happysolver.travelingsalesman.algorithm.localsearch.TSPLocalSearch;
import happysolver.travelingsalesman.api.TSPInput;
import happysolver.travelingsalesman.api.TSPSolution;

public class LinKernighanTest {

	@Test
	public void test() {
		String solutionFileName = "eil51.opt";
		String inputFileName = "eil51.tsp";

		TSPParser parser = new TSPParser();
		TSPInput tspInput = parser.parseInputFile(inputFileName);
		TSPSolution solution = parser.parseSolution(tspInput, solutionFileName);
		assertThat(solution.getValue()).isEqualTo(426d, offset(1.0));

		TSPSolution startSolution = new NearestNeighbour().run(tspInput);
		assertThat(startSolution.getValue()).isEqualTo(511d, offset(1.0));

		StopCondition stopCondition = (oldValue, newValue, iteration) -> oldValue <= newValue;

		TSPLocalSearch localSearch = new TSPLocalSearch();
		TSPSolution improve = localSearch.improve(tspInput, startSolution, stopCondition);
		assertThat(improve.getValue()).isEqualTo(438d, offset(1.0));

		improve = new LinKernighan().improve(tspInput, improve, stopCondition);
		assertThat(improve.getValue()).isEqualTo(429d, offset(1.0));
	}

}
