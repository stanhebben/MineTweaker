package stanhebben.minetweaker.base.actions;

import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.tweaker.FuelTweaker;

public class SetFuelItemAction implements IUndoableAction {
	private TweakerItem item;
	private int newValue;
	private Integer oldValue;
	
	public SetFuelItemAction(TweakerItem item, int value) {
		this.item = item;
		newValue = value;
	}

	@Override
	public void apply() {
		if (item.isSubItem()) {
			oldValue = FuelTweaker.INSTANCE.setFuelItem(item.getItemId(), item.getItemSubId(), newValue);
		} else {
			oldValue = FuelTweaker.INSTANCE.setFuelItem(item.getItemId(), newValue);
		}
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		if (item.isSubItem()) {
			FuelTweaker.INSTANCE.setFuelItem(item.getItemId(), item.getItemSubId(), oldValue);
		} else {
			FuelTweaker.INSTANCE.setFuelItem(item.getItemId(), oldValue);
		}
	}

	public String describe() {
		return "Setting the fuel value for " + item.getDisplayName() + " to " + newValue + " ticks";
	}

	public String describeUndo() {
		return "Restoring the fuel value for " + item.getDisplayName() + " to " + oldValue + " ticks";
	}
}
