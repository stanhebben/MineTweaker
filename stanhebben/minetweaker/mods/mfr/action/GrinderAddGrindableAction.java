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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
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
	private static final Random random = new Random();
	
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
		FactoryRegistry.registerGrindable(new SimpleGrindable(entityClass, drops, dropChances));
	}

	public boolean canUndo() {
		return MFRHacks.grindables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.grindables.remove(entityClass);
		} else {
			FactoryRegistry.registerGrindable(old);
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

		public List<MobDrop> grind(World world, EntityLivingBase entity, Random random) {
			List<MobDrop> dropList = new ArrayList<MobDrop>();
			for (int i = 0; i < drops.length; i++) {
				if (i >= dropChances.length || random.nextFloat() <= dropChances[i]) {
					dropList.add(new MobDrop(1, drops[i].get()));
				}
			}
			return dropList;
		}

		public boolean processEntity(EntityLivingBase entity) {
			entity.attackEntityFrom(DamageSource.generic, entity.getMaxHealth() * 20);
			return entity.isDead;
		}
	}
}
