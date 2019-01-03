package happysolver.common.localsearch;

import happysolver.common.Solution;

public interface LocalSearchExchangeHandler<S extends Solution, E extends LocalSearchExchange> {

	S doExchange(S solution, E exchange);

	double calculateNewValue(S solution, E exchange);
}
