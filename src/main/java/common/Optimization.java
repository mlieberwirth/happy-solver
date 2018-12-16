package common;

public interface Optimization<I extends Input, S extends Solution> {

	S run(I input);
}
