package common.localsearch;

public enum LocalSearchDirection {

	INCREASE {
		@Override
		public boolean isImprovement(double oldValue, double newValue) {
			return oldValue < newValue;
		}
	},
	DECREASE {
		@Override
		public boolean isImprovement(double oldValue, double newValue) {
			return oldValue > newValue;
		}
	};

	public abstract boolean isImprovement(double oldValue, double newValue);
}
