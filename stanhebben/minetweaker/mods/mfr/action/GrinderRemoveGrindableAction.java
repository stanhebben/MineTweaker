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
import powercrystals.minefactoryreloaded.api.IFactoryGrindable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class GrinderRemoveGrindableAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final IFactoryGrindable old;
	
	public GrinderRemoveGrindableAction(Class<?> entityClass) {
		this.entityClass = entityClass;
		old = MFRHacks.grindables.get(entityClass);
	}

	public void apply() {
		MFRHacks.grindables.remove(entityClass);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+FarmingRegistry.registerGrindable(old);
		//#else
		FactoryRegistry.registerGrindable(old);
		//#endif
	}

	public String describe() {
		return "Removing grindable " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Restoring grindable " + entityClass.getCanonicalName();
	}
}
