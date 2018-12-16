package binbacking.algorithm;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import binbacking.api.Bin;
import binbacking.api.BinPackingInput;
import binbacking.api.Item;
import binbacking.core.BinPackingFactory;

public class GivenOrderInsertTest {

	private final BinPackingFactory factory = new BinPackingFactory();

	@Test
	public void test_simple_insert_in_order() {

		GivenOrderInsert givenOrderInsert = new GivenOrderInsert();

		int binCapacity = 10;
		List<Item> itemsToInsert = createItems(2, 2, 3, 7, 5, 8);
		BinPackingInput input = factory.createInput(binCapacity, itemsToInsert);
		List<Bin> result = givenOrderInsert.run(input).getBinList();

		assertThat(result).hasSize(4);
		assertThat(result.get(0)).extracting(Bin::getItems)
				.containsExactly(asList(itemsToInsert.get(0), itemsToInsert.get(1), itemsToInsert.get(2)));
		assertThat(result.get(1)).extracting(Bin::getItems).containsExactly(asList(itemsToInsert.get(3)));
		assertThat(result.get(2)).extracting(Bin::getItems).containsExactly(asList(itemsToInsert.get(4)));
		assertThat(result.get(3)).extracting(Bin::getItems).containsExactly(asList(itemsToInsert.get(5)));
	}

	private List<Item> createItems(int... amounts) {
		List<Item> result = new ArrayList<>();
		for (int i = 0; i < amounts.length; i++) {
			result.add(factory.createItem(i + "", amounts[i]));
		}
		return result;
	}
}
