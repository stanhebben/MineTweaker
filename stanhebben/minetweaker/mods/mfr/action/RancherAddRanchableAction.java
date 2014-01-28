/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
//#ifdef MC152
//+import net.minecraft.entity.EntityLiving;
//+import net.minecraft.item.ItemStack;
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import net.minecraft.entity.EntityLivingBase;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.RanchedItem;
//#endif
import powercrystals.minefactoryreloaded.api.IFactoryRanchable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class RancherAddRanchableAction implements IUndoableAction {
	private static final Random random = new Random();
	
	private final Class<?> clazz;
	private final TweakerItemStack[] items;
	private final float[] itemChances;
	private final TweakerLiquidStack[] liquids;
	private final float[] liquidChances;
	private final TweakerItem[] validContainers;
	
	private final IFactoryRanchable old;
	
	public RancherAddRanchableAction(
			Class<?> clazz,
			TweakerItemStack[] items, float[] itemChances,
			TweakerLiquidStack[] liquids, float[] liquidChances,
			TweakerItem[] validContainers) {
		this.clazz = clazz;
		this.items = items;
		this.itemChances = itemChances;
		this.liquids = liquids;
		this.liquidChances = liquidChances;
		this.validContainers = validContainers;
		
		old = MFRHacks.ranchables == null ? null : MFRHacks.ranchables.get(clazz);
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerRanchable(new SimpleRanchable(clazz, items, itemChances, liquids, liquidChances, validContainers));
		//#else
		FactoryRegistry.registerRanchable(new SimpleRanchable(clazz, items, itemChances, liquids, liquidChances, validContainers));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.ranchables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.ranchables.remove(clazz);
		} else {
			//#ifdef MC152
			//+FarmingRegistry.registerRanchable(old);
			//#else
			FactoryRegistry.registerRanchable(old);
			//#endif
		}
	}

	public String describe() {
		return "Adding ranchable " + clazz.getCanonicalName();
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing ranchable " + clazz.getCanonicalName();
		} else {
			return "Restoring ranchable " + clazz.getCanonicalName();
		}
	}
	
	private static class SimpleRanchable implements IFactoryRanchable {
		private final Class<?> clazz;
		private final TweakerItemStack[] items;
		private final float[] itemChances;
		private final TweakerLiquidStack[] liquids;
		private final float[] liquidChances;
		private final TweakerItem[] validContainers;
		
		public SimpleRanchable(Class<?> clazz, TweakerItemStack[] items, float[] itemChances, TweakerLiquidStack[] liquids, float[] liquidChances, TweakerItem[] validContainers) {
			this.clazz = clazz;
			this.items = items;
			this.itemChances = itemChances;
			this.liquids = liquids;
			this.liquidChances = liquidChances;
			this.validContainers = validContainers;
		}

		public Class<?> getRanchableEntity() {
			return clazz;
		}

		//#ifdef MC152
		//+public List<ItemStack> ranch(World world, EntityLiving entity, IInventory rancher) {
			//+List<ItemStack> result = new ArrayList<ItemStack>();
		//#else
		public List<RanchedItem> ranch(World world, EntityLivingBase entity, IInventory rancher) {
			List<RanchedItem> result = new ArrayList<RanchedItem>();
		//#endif
			if (clazz.isAssignableFrom(entity.getClass())) {
				for (int i = 0; i < items.length; i++) {
					if (random.nextFloat() <= itemChances[i]) {
						//#ifdef MC152
						//+result.add(items[i].get());
						//#else
						result.add(new RanchedItem(items[i].get()));
						//#endif
					}
				}
				for (int i = 0; i < liquids.length; i++) {
					if (random.nextFloat() <= liquidChances[i]) {
						liquids[i].fill(rancher, validContainers);
					}
				}
			}
			return result;
		}
	}
}
