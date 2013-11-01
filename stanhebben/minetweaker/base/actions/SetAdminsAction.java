package stanhebben.minetweaker.base.actions;

import java.util.Arrays;
import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements minetweaker.setAdmins
 * 
 * @author Stan Hebben
 */
public final class SetAdminsAction implements IUndoableAction {
	private final String[] oldAdmins;
	private final String[] newAdmins;
	
	public SetAdminsAction(String[] admins) {
		newAdmins = admins;
		oldAdmins = MineTweaker.instance.getAdmins();
	}

	public void apply() {
		MineTweaker.instance.setAdmins(newAdmins);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		MineTweaker.instance.setAdmins(oldAdmins);
	}

	public String describe() {
		return "Settings admins to " + Arrays.toString(newAdmins);
	}

	public String describeUndo() {
		return "Restoring admins to " + Arrays.toString(oldAdmins);
	}
}
