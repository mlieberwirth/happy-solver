package happysolver.binbacking.algorithm;

import java.util.ArrayList;
import java.util.List;

import happysolver.binbacking.api.Bin;
import happysolver.binbacking.api.BinPackingInput;
import happysolver.binbacking.api.BinPackingSolution;
import happysolver.binbacking.api.Item;
import happysolver.binbacking.core.BinPackingFactory;
import happysolver.common.Optimization;

public class GivenOrderInsert implements Optimization<BinPackingInput, BinPackingSolution> {

	private final BinPackingFactory factory = new BinPackingFactory();

	@Override
	public BinPackingSolution run(BinPackingInput input) {
		List<Bin> result = new ArrayList<>();
		Bin firstBin = factory.createBin("0", input.getBinCapacity());
		result.add(firstBin);
		for (Item item : input.getItems()) {
			boolean wasInsert = false;
			for (Bin bin : result) {
				if (bin.isAddPossible(item)) {
					bin.addItem(item);
					wasInsert = true;
					break;
				}
			}
			if (!wasInsert) {
				Bin newBin = factory.createBin(result.size() + "", input.getBinCapacity());
				result.add(newBin);
				newBin.addItem(item);
			}
		}
		return factory.createSolution(result);
	}
}
