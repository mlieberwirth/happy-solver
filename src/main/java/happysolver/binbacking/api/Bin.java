package happysolver.binbacking.api;

import java.util.List;

public interface Bin {

	String getId();

	int getCapacity();

	List<Item> getItems();

	void addItem(Item item);

	void removeItem(Item item);

	boolean containsItem(Item item);

	boolean isEmpty();

	boolean isFull();

	boolean isAddPossible(Item item);

}
