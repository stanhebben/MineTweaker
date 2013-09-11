/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.core.BuildCraftAPI;
import net.minecraft.block.Block;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class SetSoftBlockAction implements IUndoableAction {
	private int id;
	private boolean newValue;
	private boolean oldValue;
	
	public SetSoftBlockAction(int id, boolean newValue) {
		this.id = id;
		this.newValue = newValue;
		this.oldValue = BuildCraftAPI.softBlocks[id];
	}

	public void apply() {
		BuildCraftAPI.softBlocks[id] = newValue;
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		BuildCraftAPI.softBlocks[id] = oldValue;
	}

	public String describe() {
		return "setting soft block status of " + Block.blocksList[id].getLocalizedName() + " to " + (newValue ? "soft" : "hard");
	}

	public String describeUndo() {
		return "setting soft block status of " + Block.blocksList[id].getLocalizedName() + " to " + (oldValue ? "soft" : "hard");
	}
}
