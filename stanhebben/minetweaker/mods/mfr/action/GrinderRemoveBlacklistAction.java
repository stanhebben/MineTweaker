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
public class GrinderRemoveBlacklistAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final boolean existed;
	
	public GrinderRemoveBlacklistAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		existed = MFRHacks.grindableBlacklist.contains(entityClass);
	}

	public void apply() {
		if (existed) MFRHacks.grindableBlacklist.remove(entityClass);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (existed) MFRHacks.grindableBlacklist.add(entityClass);
	}

	public String describe() {
		return "Removing grindable blacklist entity " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Restoring grindable blacklist entity " + entityClass.getCanonicalName();
	}
}
