package travelingsalesman.algorithm.localsearch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;
import travelingsalesman.core.TSPFactory;

public class TSPIteratorTest {

	private final TSPFactory factory = new TSPFactory();

	@Test
	public void test_with_four_vertex() {
		Vertex vA = factory.createVertex("A", 0, 1);
		Vertex vB = factory.createVertex("B", 1, 1);
		Vertex vC = factory.createVertex("C", 1, 0);
		Vertex vD = factory.createVertex("D", 0, 0);

		List<Vertex> vertexs = Arrays.asList(vA, vB, vC, vD, vA);
		double value = 4;
		TSPSolution solution = factory.createSolution(vertexs, value);

		TSPIterator iterator = new TSPIterator();
		iterator.init(solution);
		assertThat(iterator.hasNext()).isTrue();

		TSPExchange exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vA);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vB);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vC);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vD);

		assertThat(exchange.getNewEdge1().getStart()).isEqualTo(vA);
		assertThat(exchange.getNewEdge1().getEnd()).isEqualTo(vC);
		assertThat(exchange.getNewEdge2().getStart()).isEqualTo(vD);
		assertThat(exchange.getNewEdge2().getEnd()).isEqualTo(vB);

		assertThat(iterator.hasNext()).isTrue();

		exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vB);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vC);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vD);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vA);

		assertThat(exchange.getNewEdge1().getStart()).isEqualTo(vB);
		assertThat(exchange.getNewEdge1().getEnd()).isEqualTo(vD);
		assertThat(exchange.getNewEdge2().getStart()).isEqualTo(vA);
		assertThat(exchange.getNewEdge2().getEnd()).isEqualTo(vC);

		assertThat(iterator.hasNext()).isFalse();

		iterator.init(solution);
		assertThat(iterator.hasNext()).isTrue();
	}

	@Test
	public void test_with_four_five() {
		Vertex vA = factory.createVertex("A", 0, 1);
		Vertex vB = factory.createVertex("B", 1, 1);
		Vertex vC = factory.createVertex("C", 2, 1);
		Vertex vD = factory.createVertex("D", 2, 0);
		Vertex vE = factory.createVertex("E", 1, 0);
		Vertex vF = factory.createVertex("F", 0, 0);

		List<Vertex> vertexs = Arrays.asList(vA, vB, vC, vD, vE, vF, vA);
		double value = 6;
		TSPSolution solution = factory.createSolution(vertexs, value);

		TSPIterator iterator = new TSPIterator();
		iterator.init(solution);
		assertThat(iterator.hasNext()).isTrue();

		TSPExchange exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vA);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vB);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vC);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vD);

		assertThat(iterator.hasNext()).isTrue();
		exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vA);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vB);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vD);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vE);

		assertThat(iterator.hasNext()).isTrue();
		exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vA);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vB);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vE);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vF);

		assertThat(iterator.hasNext()).isTrue();
		exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vB);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vC);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vD);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vE);

		assertThat(iterator.hasNext()).isTrue();
		exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vB);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vC);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vE);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vF);

		assertThat(iterator.hasNext()).isTrue();
		exchange = iterator.next();
		assertThat(exchange.getOldEdge1().getStart()).isEqualTo(vB);
		assertThat(exchange.getOldEdge1().getEnd()).isEqualTo(vC);
		assertThat(exchange.getOldEdge2().getStart()).isEqualTo(vF);
		assertThat(exchange.getOldEdge2().getEnd()).isEqualTo(vA);
	}
}
