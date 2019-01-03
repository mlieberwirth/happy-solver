package travelingsalesman.algorithm.localsearch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import travelingsalesman.api.CostFunction;
import travelingsalesman.api.TSPInput;
import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;
import travelingsalesman.api.core.TSPFactory;

public class TSPLocalSearchTest {

	private final TSPFactory factory = new TSPFactory();

	@Test
	public void test() {
		Vertex vA = factory.createVertex("A", 0, 1);
		Vertex vB = factory.createVertex("B", 1, 1);
		Vertex vC = factory.createVertex("C", 2, 1);
		Vertex vD = factory.createVertex("D", 2, 0);
		Vertex vE = factory.createVertex("E", 1, 0);
		Vertex vF = factory.createVertex("F", 0, 0);

		List<Vertex> vertexs = Arrays.asList(vA, vE, vD, vC, vB, vF, vA);
		double value = 6.83;
		TSPSolution solution = factory.createSolution(vertexs, value);

		CostFunction costFunction = factory.createEuclideanDistance();
		TSPInput input = factory.createInput(costFunction, vertexs);
		TSPSolution solution2 = new TSPLocalSearch().improve(input, solution);

		assertThat(solution2.getPath()).containsExactly(vA, vB, vC, vD, vE, vF, vA);
		assertThat(solution2.getValue()).isEqualTo(6d, offset(0.1));
	}

}
