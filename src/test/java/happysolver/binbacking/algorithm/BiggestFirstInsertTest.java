package happysolver.binbacking.algorithm;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import happysolver.binbacking.api.Bin;
import happysolver.binbacking.api.BinPackingInput;
import happysolver.binbacking.api.Item;
import happysolver.binbacking.core.BinPackingFactory;

public class BiggestFirstInsertTest {

	private final BinPackingFactory factory = new BinPackingFactory();

	@Test
	public void test_simple_insert_in_order() {

		BiggestFirstInsert biggestFirstInsert = new BiggestFirstInsert();

		int binCapacity = 10;
		List<Item> itemsToInsert = createItems(2, 1, 4, 7, 5, 8);
		BinPackingInput input = factory.createInput(binCapacity, itemsToInsert);
		List<Bin> result = biggestFirstInsert.run(input).getBinList();

		assertThat(result).hasSize(3);
		assertThat(result.get(0)).extracting(Bin::getItems)
				.containsExactly(asList(itemsToInsert.get(5), itemsToInsert.get(0)));
		assertThat(result.get(1)).extracting(Bin::getItems)
				.containsExactly(asList(itemsToInsert.get(3), itemsToInsert.get(1)));
		assertThat(result.get(2)).extracting(Bin::getItems)
				.containsExactly(asList(itemsToInsert.get(4), itemsToInsert.get(2)));
	}

	private List<Item> createItems(int... amounts) {
		List<Item> result = new ArrayList<>();
		for (int i = 0; i < amounts.length; i++) {
			result.add(factory.createItem(i + "", amounts[i]));
		}
		return result;
	}

}
