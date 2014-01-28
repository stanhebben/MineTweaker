/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
//#ifdef MC152
//+import net.minecraft.entity.EntityLiving;
//#else
import net.minecraft.entity.EntityLivingBase;
//#endif
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.BreederRemoveFoodAction;

/**
 *
 * @author Stanneke
 */
public class BreederRemoveFoodFunction extends TweakerFunction {
	public static final BreederRemoveFoodFunction INSTANCE = new BreederRemoveFoodFunction();
	
	private BreederRemoveFoodFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) throw new TweakerExecuteException("breeder.removeFood requires 2 arguments");
		try {
			Class<?> entityClass = Class.forName(notNull(arguments[0], "entity class cannot be null").toBasicString());
			//#ifdef MC152
			//+if (!EntityLiving.class.isAssignableFrom(entityClass)) {
			//#else
			if (!EntityLivingBase.class.isAssignableFrom(entityClass)) {
			//#endif
				throw new TweakerExecuteException("class is not a living entity class");
			}
			TweakerItemPattern pattern =
					notNull(arguments[1], "food cannot be null")
					.toItemPattern("food must be an item");
			
			if (MFRHacks.breederFoods == null) {
				Tweaker.log(Level.WARNING, "breeder.removeFood is unavailable");
			} else {
				List<Integer> toRemove = new ArrayList<Integer>();
				List<ItemStack> foods = MFRHacks.breederFoods.get(entityClass);
				
				for (int i = foods.size() - 1; i >= 0; i--) {
					if (pattern.matches(foods.get(i))) {
						toRemove.add(i);
					}
				}
				for (int i : toRemove) {
					Tweaker.apply(new BreederRemoveFoodAction(entityClass, i));
				}
			}
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("could not find entity class");
		}
	}

	@Override
	public String toString() {
		return "breeder.removeFood";
	}
}
