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
public class ItemAddToolAction implements IUndoableAction {
	private final Block block;
	private final int meta;
	private final String tool;
	private final int level;
	
	private final Integer oldLevel;
	private final boolean wasEffective;
	
	public ItemAddToolAction(Block block, int meta, String tool, int level) {
		this.block = block;
		this.meta = meta;
		this.tool = tool;
		this.level = level;
		
		oldLevel = MineTweakerUtil.getBlockHarvestLevel(block, meta, tool);
		wasEffective = MineTweakerUtil.isHarvestEffective(block, meta, tool);
	}

	public void apply() {
		MinecraftForge.setBlockHarvestLevel(block, meta, tool, level);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (wasEffective && null != oldLevel) {
			MinecraftForge.setBlockHarvestLevel(block, meta, tool, oldLevel);
		} else {
			MineTweakerUtil.removeBlockTool(block, meta, tool);
		}
	}

	public String describe() {
		return "Adding " + block.blockID + ":" + meta + " tool harvest level of " + tool + " to " + level;
	}

	public String describeUndo() {
		return "Restoring " + block.blockID + ":" + meta + " tool level of " + tool + " to " + oldLevel;
	}
}
