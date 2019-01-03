package happysolver.binbacking.api;

import java.util.List;

import happysolver.common.Solution;

public interface BinPackingSolution extends Solution {

	List<Bin> getBinList();
}
