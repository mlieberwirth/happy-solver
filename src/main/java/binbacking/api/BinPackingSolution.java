package binbacking.api;

import java.util.List;

import binbacking.api.Bin;
import common.Solution;

public interface BinPackingSolution extends Solution {

	List<Bin> getBinList();
}
