package binbacking.core;

import java.util.List;

import binbacking.api.BinPackingInput;
import binbacking.api.Item;

class BinPackingInputImpl implements BinPackingInput {

	private final int binCapacity;
	private final List<Item> itemList;

	public BinPackingInputImpl(int binCapacity, List<Item> itemList) {
		this.binCapacity = binCapacity;
		this.itemList = itemList;
	}

	@Override
	public int getBinCapacity() {
		return binCapacity;
	}

	@Override
	public List<Item> getItems() {
		return itemList;
	}

}
