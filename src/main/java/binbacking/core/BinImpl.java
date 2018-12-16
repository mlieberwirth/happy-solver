package binbacking.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import binbacking.api.Bin;
import binbacking.api.Item;

class BinImpl implements Bin {

	private final String id;
	private final int capacity;
	private final List<Item> itemList;

	private int amount;

	public BinImpl(String id, int capacity) {
		this.id = id;
		this.capacity = capacity;
		this.itemList = new ArrayList<>();
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(itemList);
	}

	public void addItem(Item item) {
		itemList.add(item);
		amount += item.getAmount();
	}

	public void removeItem(Item item) {
		itemList.remove(item);
		amount -= item.getAmount();
	}

	public boolean containsItem(Item item) {
		return itemList.contains(item);
	}

	public boolean isEmpty() {
		return itemList.isEmpty();
	}

	public boolean isFull() {
		return amount >= capacity;
	}

	public boolean isAddPossible(Item item) {
		return item.getAmount() + amount <= capacity;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Bin: " + id + " contains " + itemList.size() + " ("
				+ itemList.stream().map(Item::toString).collect(Collectors.joining(", ")) + ")";
	}
}
