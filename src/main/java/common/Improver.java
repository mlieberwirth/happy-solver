package common;

public interface Improver<I extends Input, S extends Solution> {

	S improve(I input, S startSolution);

	S improve(I input, S startSolution, StopCondition stopCondition);
}
