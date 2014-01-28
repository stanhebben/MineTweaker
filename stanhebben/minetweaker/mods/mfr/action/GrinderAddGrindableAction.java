/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//#ifdef MC152
//+import net.minecraft.entity.EntityLiving;
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import net.minecraft.entity.EntityLivingBase;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
//#endif
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryGrindable;
import powercrystals.minefactoryreloaded.api.MobDrop;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class GrinderAddGrindableAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final TweakerItemStack[] drops;
	private final float[] dropChances;
	
	private final IFactoryGrindable old;
	
	public GrinderAddGrindableAction(Class<?> entityClass, TweakerItemStack[] drops, float[] dropChances) {
		this.entityClass = entityClass;
		this.drops = drops;
		this.dropChances = dropChances;
		
		old = MFRHacks.grindables == null ? null : MFRHacks.grindables.get(entityClass);
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerGrindable(new SimpleGrindable(entityClass, drops, dropChances));
		//#else
		FactoryRegistry.registerGrindable(new SimpleGrindable(entityClass, drops, dropChances));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.grindables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.grindables.remove(entityClass);
		} else {
			//#ifdef MC152
			//+FarmingRegistry.registerGrindable(old);
			//#else
			FactoryRegistry.registerGrindable(old);
			//#endif
		}
	}

	public String describe() {
		return "Adding grindable mob " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing grindable mob " + entityClass.getCanonicalName();
		} else {
			return "Restoring grindable mob " + entityClass.getCanonicalName();
		}
	}
	
	private static class SimpleGrindable implements IFactoryGrindable {
		private final Class<?> entityClass;
		private final TweakerItemStack[] drops;
		private final float[] dropChances;
		
		public SimpleGrindable(Class<?> entityClass, TweakerItemStack[] drops, float[] dropChances) {
			this.entityClass = entityClass;
			this.drops = drops;
			this.dropChances = dropChances;
		}

		public Class<?> getGrindableEntity() {
			return entityClass;
		}

		//#ifdef MC152
		//+public List<MobDrop> grind(World world, EntityLiving entity, Random random) {
		//#else
		public List<MobDrop> grind(World world, EntityLivingBase entity, Random random) {
		//#endif
			List<MobDrop> dropList = new ArrayList<MobDrop>();
			for (int i = 0; i < drops.length; i++) {
				if (i >= dropChances.length || random.nextFloat() <= dropChances[i]) {
					dropList.add(new MobDrop(1, drops[i].get()));
				}
			}
			return dropList;
		}

		//#ifdef MC152
		//+public boolean processEntity(EntityLiving entity) {
		//#else
		public boolean processEntity(EntityLivingBase entity) {
		//#endif
			entity.attackEntityFrom(DamageSource.generic, entity.getMaxHealth() * 20);
			return entity.isDead;
		}
	}
}
