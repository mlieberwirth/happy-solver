package binbacking.core;

import java.util.List;

import binbacking.api.Bin;
import binbacking.api.BinPackingInput;
import binbacking.api.BinPackingSolution;
import binbacking.api.Item;

public class BinPackingFactory {

	public Bin createBin(String id, int capacity) {
		return new BinImpl(id, capacity);
	}

	public Item createItem(String id, int amount) {
		return new ItemImpl(id, amount);
	}

	public BinPackingSolution createSolution(List<Bin> binList) {
		return new BinPackingSolutionImpl(binList);
	}
	
	public BinPackingInput createInput(int capacity, List<Item> itemList){
		return new BinPackingInputImpl(capacity, itemList);
	}
}
