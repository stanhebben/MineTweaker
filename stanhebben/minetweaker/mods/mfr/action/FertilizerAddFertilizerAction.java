/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import net.minecraft.item.ItemStack;
//#ifdef MC152
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
//#endif
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizer;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FertilizerAddFertilizerAction implements IUndoableAction {
	private final TweakerItem item;
	private final FertilizerType type;
	
	private final IFactoryFertilizer old;
	
	public FertilizerAddFertilizerAction(TweakerItem item, FertilizerType type) {
		this.item = item;
		this.type = type;
		
		old = MFRHacks.fertilizers == null ? null : MFRHacks.fertilizers.get(item.getItemId());
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerFertilizer(new SimpleFertilizer(item, type));
		//#else
		FactoryRegistry.registerFertilizer(new SimpleFertilizer(item, type));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.fertilizers != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.fertilizers.remove(item.getItemId());
		} else {
			//#ifdef MC152
			//+FarmingRegistry.registerFertilizer(old);
			//#else
			FactoryRegistry.registerFertilizer(old);
			//#endif
		}
	}

	public String describe() {
		return "Adding fertilizer " + item.getDisplayName();
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing fertilizer " + item.getDisplayName();
		} else {
			return "Restoring fertilizer " + item.getDisplayName();
		}
	}
	
	private static class SimpleFertilizer implements IFactoryFertilizer {
		private final TweakerItem item;
		private final FertilizerType type;
		
		public SimpleFertilizer(TweakerItem item, FertilizerType type) {
			this.item = item;
			this.type = type;
		}

		public int getFertilizerId() {
			return item.getItemId();
		}

		public int getFertilizerMeta() {
			return item.getItemSubId();
		}

		public FertilizerType getFertilizerType() {
			return type;
		}

		public void consume(ItemStack fertilizer) {
			fertilizer.stackSize--;
		}
	}
}
