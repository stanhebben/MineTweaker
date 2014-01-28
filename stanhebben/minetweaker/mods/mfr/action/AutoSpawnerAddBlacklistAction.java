/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import net.minecraft.entity.EntityList;
//#ifdef MC152
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class AutoSpawnerAddBlacklistAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final boolean existed;
	
	public AutoSpawnerAddBlacklistAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		existed = MFRHacks.autoSpawnerBlacklist == null ? false : MFRHacks.autoSpawnerBlacklist.contains(entityClass);
	}

	public void apply() {
		//#ifdef MC152
		//+if (!existed) FarmingRegistry.registerAutoSpawnerBlacklist((String) EntityList.classToStringMapping.get(entityClass));
		//#else
		if (!existed) FactoryRegistry.registerAutoSpawnerBlacklist((String) EntityList.classToStringMapping.get(entityClass));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.autoSpawnerBlacklist != null;
	}

	public void undo() {
		if (!existed) MFRHacks.autoSpawnerBlacklist.remove(entityClass);
	}

	public String describe() {
		return "Adding auto-spawner blacklist " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Removing auto-spawner blacklist " + entityClass.getCanonicalName();
	}
}
