package happysolver.binbacking.algorithm;

import java.util.List;

import happysolver.binbacking.api.BinPackingInput;
import happysolver.binbacking.api.BinPackingSolution;
import happysolver.binbacking.api.Item;
import happysolver.binbacking.core.BinPackingFactory;
import happysolver.common.Optimization;

public class BiggestFirstInsert implements Optimization<BinPackingInput, BinPackingSolution> {

	private final ItemSorter itemSorter = new ItemSorter();
	private final GivenOrderInsert givenOrderInsert = new GivenOrderInsert();
	private final BinPackingFactory factory = new BinPackingFactory();;

	@Override
	public BinPackingSolution run(BinPackingInput input) {
		List<Item> sortedItems = itemSorter.biggestFirst(input.getItems());
		BinPackingInput sortedInput = factory.createInput(input.getBinCapacity(), sortedItems);
		return givenOrderInsert.run(sortedInput);
	}
}
