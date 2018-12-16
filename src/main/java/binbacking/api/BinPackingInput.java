package binbacking.api;

import java.util.List;

import common.Input;

public interface BinPackingInput extends Input {

	int getBinCapacity();

	List<Item> getItems();
}
