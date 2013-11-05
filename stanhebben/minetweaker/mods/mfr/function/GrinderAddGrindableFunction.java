/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import net.minecraft.entity.EntityLivingBase;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.GrinderAddGrindableAction;

/**
 *
 * @author Stanneke
 */
public class GrinderAddGrindableFunction extends TweakerFunction {
	public static final GrinderAddGrindableFunction INSTANCE = new GrinderAddGrindableFunction();
	
	private GrinderAddGrindableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 1 || arguments.length > 3) throw new TweakerExecuteException("grinder.addGrindable requires 1-3 arguments");
		try {
			Class<?> entityClass = Class.forName(
					notNull(arguments[0], "class cannot be null")
							.toBasicString());
			if (!EntityLivingBase.class.isAssignableFrom(entityClass)) {
				throw new TweakerExecuteException("entity class is not a living entity");
			}
			TweakerItemStack[] possibleDrops;
			if (arguments.length < 2 || arguments[1] == null) {
				possibleDrops = new TweakerItemStack[0];
			} else if (arguments[1].asItemStack() != null) {
				possibleDrops = new TweakerItemStack[] { arguments[1].asItemStack() };
			} else if (arguments[1].asArray() != null) {
				TweakerArray dropsArray = arguments[1].asArray();
				possibleDrops = new TweakerItemStack[dropsArray.size()];
				for (int i = 0; i < possibleDrops.length; i++) {
					possibleDrops[i] =
							notNull(dropsArray.get(i), "drop cannot be null")
							.toItemStack("drop must be an item stack");
				}
			} else {
				throw new TweakerExecuteException("drops must be null, an item stack, or an array of item stacks");
			}
			
			float[] chances;
			if (arguments.length < 3 || arguments[2] == null) {
				chances = new float[0];
			} else if (arguments[2].asArray() != null) {
				TweakerArray chancesArray = arguments[2].asArray();
				chances = new float[chancesArray.size()];
				for (int i = 0; i < chances.length; i++) {
					chances[i] =
							notNull(chancesArray.get(i), "chance cannot be null")
							.toFloat("chance must be a float").get();
				}
			} else {
				chances = new float[] { arguments[2].toFloat("chance must be a float or array of floats").get() };
			}
			
			Tweaker.apply(new GrinderAddGrindableAction(entityClass, possibleDrops, chances));
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("No such entity: " + arguments[0].toBasicString());
		}
	}

	@Override
	public String toString() {
		return "grinder.addGrindable";
	}
}
