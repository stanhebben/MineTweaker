/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.base.actions;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class ItemRemoveToolAction implements IUndoableAction {
	private final Block block;
	private final int meta;
	private final String tool;
	
	private final boolean wasEffective;
	private final Integer oldLevel;
	
	public ItemRemoveToolAction(Block block, int meta, String tool) {
		this.block = block;
		this.meta = meta;
		this.tool = tool;
		
		oldLevel = MineTweakerUtil.getBlockHarvestLevel(block, meta, tool);
		wasEffective = MineTweakerUtil.isHarvestEffective(block, meta, tool);
	}

	public void apply() {
		MineTweakerUtil.removeBlockTool(block, meta, tool);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (wasEffective && oldLevel != null) {
			MinecraftForge.setBlockHarvestLevel(block, meta, tool, oldLevel);
		}
	}

	public String describe() {
		return "Removing tool class " + tool + " from block " + block.blockID + ":" + meta;
	}

	public String describeUndo() {
		return "Restoring tool class " + tool + " to block " + block.blockID + ":" + meta;
	}
}
