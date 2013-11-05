/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FertilizerAddFertilizableAction implements IUndoableAction {
	private final TweakerItem block;
	private final TweakerItem plant;
	private final FertilizerType type;
	
	private final IFactoryFertilizable old;

	public FertilizerAddFertilizableAction(TweakerItem block, TweakerItem plant, FertilizerType type) {
		this.block = block;
		this.plant = plant;
		this.type = type;
		
		old = MFRHacks.fertilizables == null ? null : MFRHacks.fertilizables.get(block.getItemId());
	}
	
	public void apply() {
		FactoryRegistry.registerFertilizable(new SimpleFertilizable(block, plant, type));
	}

	public boolean canUndo() {
		return MFRHacks.fertilizables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.fertilizables.remove(block.getItemId());
		} else {
			FactoryRegistry.registerFertilizable(old);
		}
	}

	public String describe() {
		return "Making " + block.getDisplayName() + " fertilizable";
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing fertilizable " + block.getDisplayName();
		} else {
			return "Restoring fertilizable " + block.getDisplayName();
		}
	}
	
	private static class SimpleFertilizable implements IFactoryFertilizable {
		private final TweakerItem block;
		private final TweakerItem plant;
		private final FertilizerType type;
		
		public SimpleFertilizable(TweakerItem block, TweakerItem plant, FertilizerType type) {
			this.block = block;
			this.plant = plant;
			this.type = type;
		}

		public int getFertilizableBlockId() {
			return block.getItemId();
		}

		public boolean canFertilizeBlock(World world, int x, int y, int z, FertilizerType fertilizerType) {
			return (this.type == null || type == fertilizerType)
					&& world.getBlockId(x, y, z) == block.getItemId()
					&& (!block.isSubItem() || block.getItemSubId() == OreDictionary.WILDCARD_VALUE || block.getItemSubId() == world.getBlockMetadata(x, y, z));
		}

		public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType) {
			if (plant == null) {
				return ItemDye.applyBonemeal(new ItemStack(Item.dyePowder.itemID, 1, 15), world, x, y, z, null);
			} else {
				world.setBlock(x, y, z, plant.getItemId(), plant.getItemSubId(), 0);
				return true;
			}
		}
	}
}
