package travelingsalesman.algorithm;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import travelingsalesman.api.CostFunction;
import travelingsalesman.api.TSPInput;
import travelingsalesman.api.TSPSolution;
import travelingsalesman.api.Vertex;
import travelingsalesman.api.core.TSPFactory;

public class TSPParser {

	private final TSPFactory factory = new TSPFactory();

	private static final Set<String> IGNORE_LINES_WITH = new HashSet<>(
			asList("NAME", "COMMENT", "EOF", "TYPE", "DIM", "EDGE", "NODE", "-1", "TOUR"));

	private File getResourceFile(String fileName) {
		Path resource = Paths.get("src", "test", "resources", fileName);
		return resource.toFile();
	}

	public TSPInput parseInputFile(String inputFileName) {
		File file = getResourceFile(inputFileName);
		List<Vertex> vertexList = parseVertex(file);
		for (int i = 0; i < vertexList.size(); i++) {
			vertexList.get(i).setIndex(i);
		}
		CostFunction costFunction = factory.createEuclideanDistanceRound();
		return factory.createInput(costFunction, vertexList);
	}

	private List<Vertex> parseVertex(File file) {
		try (Stream<String> lines = Files.lines(file.toPath())) {
			return lines.filter(this::linesFilter).map(this::createVertex).collect(toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean linesFilter(String line) {
		return IGNORE_LINES_WITH.stream().noneMatch(ignore -> line.contains(ignore));
	}

	private Vertex createVertex(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, " ");
		String id = tokenizer.nextToken().trim();

		int x = (int) Double.parseDouble(tokenizer.nextToken().trim());
		int y = (int) Double.parseDouble(tokenizer.nextToken().trim());
		return factory.createVertex(id, x, y);
	}

	public TSPSolution parseSolution(TSPInput tspInput, String solutionFileName) {
		List<Vertex> vertexList = tspInput.getVertexList();
		Map<String, Vertex> idVertexMap = new HashMap<>();
		for (Vertex vertex : vertexList) {
			idVertexMap.put(vertex.getId(), vertex);
		}

		File solutionFile = getResourceFile(solutionFileName);
		List<String> list = parseSolutionIds(solutionFile);
		List<Vertex> solutionPath = list.stream().map(idVertexMap::get).collect(toList());
		solutionPath.add(solutionPath.get(0));
		CostFunction costFunction = factory.createEuclideanDistanceRound();
		double value = calculateValue(solutionPath, costFunction);
		return factory.createSolution(solutionPath, value);
	}

	private double calculateValue(List<Vertex> vertexList, CostFunction costFunction) {
		double result = 0;
		Vertex vertexBefore = vertexList.get(0);
		for (int i = 1; i < vertexList.size(); i++) {
			Vertex vertex = vertexList.get(i);
			result += costFunction.getCosts(vertexBefore, vertex);
			vertexBefore = vertex;
		}
		return result;
	}

	public List<String> parseSolutionIds(File file) {
		try (Stream<String> lines = Files.lines(file.toPath())) {
			return lines.filter(this::linesFilter).collect(toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
