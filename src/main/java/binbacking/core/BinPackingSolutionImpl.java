package binbacking.core;

import java.util.Collections;
import java.util.List;

import binbacking.api.Bin;
import binbacking.api.BinPackingSolution;

class BinPackingSolutionImpl implements BinPackingSolution {

	private final List<Bin> binList;

	public BinPackingSolutionImpl(List<Bin> binList) {
		this.binList = Collections.unmodifiableList(binList);
	}

	@Override
	public List<Bin> getBinList() {
		return binList;
	}
}
