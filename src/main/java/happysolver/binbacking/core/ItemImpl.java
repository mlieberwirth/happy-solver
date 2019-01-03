package happysolver.binbacking.core;

import happysolver.binbacking.api.Item;

class ItemImpl implements Item {

	private final String id;

	private final int amount;

	public ItemImpl(String id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return id + ":" + amount;
	}
}
