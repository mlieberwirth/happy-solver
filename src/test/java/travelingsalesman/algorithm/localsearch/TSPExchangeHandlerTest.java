package travelingsalesman.algorithm.localsearch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import travelingsalesman.api.CostFunction;
import travelingsalesman.api.Edge;
import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;
import travelingsalesman.api.core.TSPFactory;

public class TSPExchangeHandlerTest {

	private final TSPFactory factory = new TSPFactory();

	@Test
	public void test_exchange_costs() {
		CostFunction costFunction = factory.createEuclideanDistance();

		Vertex vA = factory.createVertex("A", 0, 1);
		Vertex vB = factory.createVertex("B", 1, 1);
		Vertex vC = factory.createVertex("C", 1, 0);
		Vertex vD = factory.createVertex("D", 0, 0);

		List<Vertex> vertexs = Arrays.asList(vA, vB, vC, vD, vA);
		double value = costFunction.getCosts(vA, vB) + costFunction.getCosts(vB, vC) + costFunction.getCosts(vC, vD)
				+ costFunction.getCosts(vD, vA);
		TSPSolution solution = factory.createSolution(vertexs, value);

		Edge oldEdge1 = factory.createEdge(vA, vB);
		Edge oldEdge2 = factory.createEdge(vC, vD);
		Edge newEdge1 = factory.createEdge(vA, vC);
		Edge newEdge2 = factory.createEdge(vD, vB);
		TSPExchange exchange = new TSPExchange(oldEdge1, oldEdge2, newEdge1, newEdge2);

		TSPExchangeHandler exchangeHandler = new TSPExchangeHandler(costFunction);
		double newValue = exchangeHandler.calculateNewValue(solution, exchange);

		assertThat(newValue).isEqualTo(4.83, offset(0.1));
	}

	@Test
	public void test_do_exchange() {
		CostFunction costFunction = factory.createEuclideanDistance();

		Vertex vA = factory.createVertex("A", 0, 1);
		Vertex vB = factory.createVertex("B", 1, 1);
		Vertex vC = factory.createVertex("C", 2, 1);
		Vertex vD = factory.createVertex("D", 2, 0);
		Vertex vE = factory.createVertex("E", 1, 0);
		Vertex vF = factory.createVertex("F", 0, 0);

		List<Vertex> vertexs = Arrays.asList(vA, vB, vC, vD, vE, vF, vA);
		double value = costFunction.getCosts(vA, vB) + costFunction.getCosts(vB, vC) + costFunction.getCosts(vC, vD)
				+ costFunction.getCosts(vD, vE) + costFunction.getCosts(vE, vF) + costFunction.getCosts(vF, vA);

		TSPSolution solution = factory.createSolution(vertexs, value);

		Edge oldEdge1 = factory.createEdge(vA, vB);
		Edge oldEdge2 = factory.createEdge(vE, vF);
		Edge newEdge1 = factory.createEdge(vA, vE);
		Edge newEdge2 = factory.createEdge(vB, vF);
		TSPExchange exchange = new TSPExchange(oldEdge1, oldEdge2, newEdge1, newEdge2);

		TSPExchangeHandler exchangeHandler = new TSPExchangeHandler(costFunction);
		TSPSolution solution2 = exchangeHandler.doExchange(solution, exchange);

		assertThat(solution2.getPath()).containsExactly(vA, vE, vD, vC, vB, vF, vA);
		assertThat(solution2.getValue()).isEqualTo(6.83, offset(0.1));
	}
}
