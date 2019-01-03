package happysolver.common;

public interface StopCondition {

	boolean doTerminate(double oldValue, double value, int iteration);

}
