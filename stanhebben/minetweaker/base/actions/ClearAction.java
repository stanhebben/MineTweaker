package stanhebben.minetweaker.base.actions;

import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.util.DefaultUndoStack;
import stanhebben.minetweaker.api.IUndoableAction;

public class ClearAction implements IUndoableAction {
	private DefaultUndoStack old;
	
	public ClearAction() {}

	@Override
	public void apply() {
		old = MineTweaker.instance.clear();
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		MineTweaker.instance.undoClear(old);
	}

	public String describe() {
		return "Clearing all actions";
	}

	public String describeUndo() {
		return "Restoring all actions that have been cleared";
	}
}
