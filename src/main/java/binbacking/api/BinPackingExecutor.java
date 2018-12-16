package binbacking.api;

import common.Optimization;

public class BinPackingExecutor {

	public BinPackingSolution run(BinPackingInput input, AlgorithmType type) {
		Optimization<BinPackingInput, BinPackingSolution> optimization = type.getOptimization();
		return optimization.run(input);
	}
}
