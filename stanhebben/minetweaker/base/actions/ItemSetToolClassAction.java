/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.base.actions;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class ItemSetToolClassAction implements IUndoableAction {
	private final Item item;
	private final String cls;
	private final int level;
	
	private final List oldValue;
	
	public ItemSetToolClassAction(Item item, String cls, int level) {
		this.item = item;
		this.cls = cls;
		this.level = level;
		
		oldValue = MineTweakerUtil.getToolClass(item);
	}

	public void apply() {
		MinecraftForge.setToolClass(item, cls, level);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (oldValue != null) {
			MineTweakerUtil.setToolClass(item, oldValue);
		}
	}

	public String describe() {
		return "Setting " + item.getUnlocalizedName() + " tool class to " + cls + " level " + level;
	}

	public String describeUndo() {
		return "Restoring " + item.getUnlocalizedName() + " tool class";
	}
}
