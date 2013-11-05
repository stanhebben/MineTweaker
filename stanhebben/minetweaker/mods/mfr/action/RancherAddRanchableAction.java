/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryRanchable;
import powercrystals.minefactoryreloaded.api.RanchedItem;
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
		FactoryRegistry.registerRanchable(new SimpleRanchable(clazz, items, itemChances, liquids, liquidChances, validContainers));
	}

	public boolean canUndo() {
		return MFRHacks.ranchables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.ranchables.remove(clazz);
		} else {
			FactoryRegistry.registerRanchable(old);
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

		public List<RanchedItem> ranch(World world, EntityLivingBase entity, IInventory rancher) {
			List<RanchedItem> result = new ArrayList<RanchedItem>();
			if (clazz.isAssignableFrom(entity.getClass())) {
				for (int i = 0; i < items.length; i++) {
					if (random.nextFloat() <= itemChances[i]) {
						result.add(new RanchedItem(items[i].get()));
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
