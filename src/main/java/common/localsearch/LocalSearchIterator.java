package common.localsearch;

import java.util.Iterator;

import common.Solution;

public interface LocalSearchIterator<S extends Solution, E extends LocalSearchExchange> extends Iterator<E> {

	void init(S solution);

	void update(S newsolution, E currentExchange);
}
