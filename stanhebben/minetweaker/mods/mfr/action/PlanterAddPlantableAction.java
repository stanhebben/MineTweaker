/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
//#ifdef MC152
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
//#endif
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.PrePlantAction;

/**
 *
 * @author Stanneke
 */
public class PlanterAddPlantableAction implements IUndoableAction {
	private final TweakerItem seed;
	private final TweakerItem plant;
	private final PrePlantAction preAction;
	
	private final IFactoryPlantable old;
	
	public PlanterAddPlantableAction(TweakerItem seed, TweakerItem plant, PrePlantAction preAction) {
		this.seed = seed;
		this.plant = plant;
		this.preAction = preAction;
		
		old = MFRHacks.plantables == null ? null : MFRHacks.plantables.get(seed.getItemId());
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerPlantable(new SimpleFactoryPlantable(
		//#else
		FactoryRegistry.registerPlantable(new SimpleFactoryPlantable(
		//#endif
				seed.getItemId(), seed.getItemSubId(),
				plant.getItemId(), plant.getItemSubId(),
				preAction));
	}

	public boolean canUndo() {
		return MFRHacks.plantables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.plantables.remove(seed.getItemId());
		} else {
			//#ifdef MC152
			//+FarmingRegistry.registerPlantable(old);
			//#else
			FactoryRegistry.registerPlantable(old);
			//#endif
		}
	}

	public String describe() {
		return "Adding plantable " + seed.getDisplayName();
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing plantable " + seed.getDisplayName();
		} else {
			return "Restoring plantable " + seed.getDisplayName();
		}
	}
	
	private static class SimpleFactoryPlantable implements IFactoryPlantable {
		private final int seedId;
		private final int seedMeta;
		private final int plantId;
		private final int plantMeta;
		private final PrePlantAction preAction;
		
		public SimpleFactoryPlantable(int seedId, int seedMeta, int plantId, int plantMeta, PrePlantAction preAction) {
			this.seedId = seedId;
			this.seedMeta = seedMeta;
			this.plantId = plantId;
			this.plantMeta = plantMeta;
			this.preAction = preAction;
		}
		
		public int getSeedId() {
			return seedId;
		}

		public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack) {
			return plantId;
		}

		public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack) {
			return plantMeta;
		}

		public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
			if (preAction == PrePlantAction.TILL
					&& world.getBlockId(x, y, z) != Block.dirt.blockID
					&& world.getBlockId(x, y, z) != Block.grass.blockID) return false;
			return stack.itemID == seedId && (seedMeta == OreDictionary.WILDCARD_VALUE || seedMeta == stack.getItemDamage())
					&& Block.blocksList[world.getBlockId(x, y, z)].isAirBlock(world, x, y, z);
		}

		public void prePlant(World world, int x, int y, int z, ItemStack stack) {
			if (preAction == PrePlantAction.TILL) {
				world.setBlock(x, y, z, Block.tilledField.blockID);
			}
		}

		public void postPlant(World world, int x, int y, int z, ItemStack stack) {
		}
	}
}
