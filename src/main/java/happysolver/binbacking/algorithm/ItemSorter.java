package happysolver.binbacking.algorithm;

import static java.util.stream.Collectors.toList;

import java.util.List;

import happysolver.binbacking.api.Item;

public class ItemSorter {

	public List<Item> biggestFirst(List<Item> items) {
		return items.stream().sorted((i1, i2) -> Integer.compare(i2.getAmount(), i1.getAmount())).collect(toList());
	}
}
