package common;

import java.util.List;

public interface RandomOptimization<I extends Input, S extends Solution> extends Optimization<I, S> {

	List<S> run(I input, int numberRuns);

}
