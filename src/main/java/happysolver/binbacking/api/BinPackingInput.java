package happysolver.binbacking.api;

import java.util.List;

import happysolver.common.Input;

public interface BinPackingInput extends Input {

	int getBinCapacity();

	List<Item> getItems();
}
