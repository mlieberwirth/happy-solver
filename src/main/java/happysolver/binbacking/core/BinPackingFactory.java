package happysolver.binbacking.core;

import java.util.List;

import happysolver.binbacking.api.Bin;
import happysolver.binbacking.api.BinPackingInput;
import happysolver.binbacking.api.BinPackingSolution;
import happysolver.binbacking.api.Item;

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
