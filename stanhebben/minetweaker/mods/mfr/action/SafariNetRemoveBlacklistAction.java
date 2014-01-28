/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class SafariNetRemoveBlacklistAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final boolean existed;
	
	public SafariNetRemoveBlacklistAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		existed = MFRHacks.safariNetBlacklist.contains(entityClass);
	}

	public void apply() {
		if (existed) MFRHacks.safariNetBlacklist.remove(entityClass);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (existed) MFRHacks.safariNetBlacklist.add(entityClass);
	}

	public String describe() {
		return "Removing safari net blacklist " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Restoring safari net blacklist " + entityClass.getCanonicalName();
	}
}
