package binbacking.api;

import binbacking.algorithm.BiggestFirstInsert;
import binbacking.algorithm.GivenOrderInsert;
import common.Optimization;

public enum AlgorithmType {

	GIVEN_ORDER(new GivenOrderInsert()),

	BIGGEST_FIRST(new BiggestFirstInsert()),;

	private final Optimization<BinPackingInput, BinPackingSolution> optimization;

	private AlgorithmType(Optimization<BinPackingInput, BinPackingSolution> optimization) {
		this.optimization = optimization;
	}

	public Optimization<BinPackingInput, BinPackingSolution> getOptimization() {
		return optimization;
	}
}
