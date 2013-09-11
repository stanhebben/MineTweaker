package stanhebben.minetweaker.base.actions;

import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.tweaker.FuelTweaker;
import stanhebben.minetweaker.tweaker.SetFuelPattern;

public class SetFuelPatternAction implements IUndoableAction {
	private SetFuelPattern pattern;
	
	public SetFuelPatternAction(TweakerItemStackPattern pattern, int value) {
		this.pattern = new SetFuelPattern(pattern, value);
	}

	@Override
	public void apply() {
		FuelTweaker.INSTANCE.addFuelPattern(pattern);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		FuelTweaker.INSTANCE.removeFuelPattern(pattern);
	}

	public String describe() {
		return "Setting the fuel value for all items matching " + pattern.getPattern().toPatternString() + " to " + pattern.getValue();
	}

	public String describeUndo() {
		return "Removing the " + pattern.getPattern().toPatternString() + " fuel pattern";
	}
}
