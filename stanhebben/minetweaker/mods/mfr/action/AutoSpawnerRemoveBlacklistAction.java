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
public class AutoSpawnerRemoveBlacklistAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final boolean existed;
	
	public AutoSpawnerRemoveBlacklistAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		existed = MFRHacks.autoSpawnerBlacklist.contains(entityClass);
	}

	public void apply() {
		if (existed) MFRHacks.autoSpawnerBlacklist.remove(entityClass);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (existed) MFRHacks.autoSpawnerBlacklist.add(entityClass);
	}

	public String describe() {
		return "Removing auto-spawner blacklist " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Restoring auto-spawner blacklist " + entityClass.getCanonicalName();
	}
}
