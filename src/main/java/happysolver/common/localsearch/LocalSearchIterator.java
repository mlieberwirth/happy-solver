package happysolver.common.localsearch;

import java.util.Iterator;

import happysolver.common.Solution;

public interface LocalSearchIterator<S extends Solution, E extends LocalSearchExchange> extends Iterator<E> {

	void init(S solution);

	void update(S newsolution, E currentExchange);
}
