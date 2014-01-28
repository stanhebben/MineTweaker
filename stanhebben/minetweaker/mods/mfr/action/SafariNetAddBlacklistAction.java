/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

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
public class SafariNetAddBlacklistAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final boolean existed;
	
	public SafariNetAddBlacklistAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		existed = MFRHacks.safariNetBlacklist == null ? false : MFRHacks.safariNetBlacklist.contains(entityClass);
	}

	public void apply() {
		//#ifdef MC152
		//+if (!existed) FarmingRegistry.registerSafariNetBlacklist(entityClass);
		//#else
		if (!existed) FactoryRegistry.registerSafariNetBlacklist(entityClass);
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.safariNetBlacklist != null;
	}

	public void undo() {
		if (!existed) MFRHacks.safariNetBlacklist.remove(entityClass);
	}

	public String describe() {
		return "Blacklisting " + entityClass.getCanonicalName() + " on the safari net";
	}

	public String describeUndo() {
		return "Unblacklisting " + entityClass.getCanonicalName() + " on the safari net";
	}
}
