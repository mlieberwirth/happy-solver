package happysolver.binbacking.core;

import java.util.Collections;
import java.util.List;

import happysolver.binbacking.api.Bin;
import happysolver.binbacking.api.BinPackingSolution;

class BinPackingSolutionImpl implements BinPackingSolution {

	private final List<Bin> binList;

	public BinPackingSolutionImpl(List<Bin> binList) {
		this.binList = Collections.unmodifiableList(binList);
	}

	@Override
	public List<Bin> getBinList() {
		return binList;
	}

	@Override
	public double getValue() {
		return binList.size();
	}
}
