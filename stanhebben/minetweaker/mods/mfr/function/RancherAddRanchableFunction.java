/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemSimple;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.RancherAddRanchableAction;

/**
 *
 * @author Stanneke
 */
public class RancherAddRanchableFunction extends TweakerFunction {
	public static final RancherAddRanchableFunction INSTANCE = new RancherAddRanchableFunction();
	private static final TweakerItem[] DEFAULT_VALID_CONTAINERS = new TweakerItem[] { new TweakerItemSimple(325) };
	
	private RancherAddRanchableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2 || arguments.length > 6) throw new TweakerExecuteException("rancher.addRanchable requires 1-4 arguments");
		try {
			Class<?> clazz = Class.forName(notNull(arguments[0], "class name cannot be null").toBasicString());
			if (!EntityLivingBase.class.isAssignableFrom(clazz)) throw new TweakerExecuteException("The class does not extend EntityLivingBase");
			
			List<TweakerItemStack> items = new ArrayList<TweakerItemStack>();
			List<Float> itemChances = new ArrayList<Float>();
			List<TweakerLiquidStack> liquids = new ArrayList<TweakerLiquidStack>();
			List<Float> liquidChances = new ArrayList<Float>();
			
			float[] chances = new float[0];
			if (arguments.length > 2 && arguments[2] != null) {
				if (arguments[2].asArray() != null) {
					TweakerArray chancesArray = arguments[2].asArray();
					chances = new float[chancesArray.size()];
					for (int i = 0; i < chances.length; i++) {
						chances[i] =
								notNull(chancesArray.get(i), "chances cannot contain null values")
								.toFloat("chances can only contain float values").get();
					}
				} else {
					chances = new float[] { arguments[2].toFloat("chances must be a float or float array").get() };
				}
			}
			
			notNull(arguments[1], "results cannot be null");
			if (arguments[1].asArray() != null) {
				TweakerArray resultsArray = arguments[1].asArray();
				for (int i = 0; i < resultsArray.size(); i++) {
					TweakerValue value = resultsArray.get(i);
					notNull(value, "a result cannot be null");
					if (value.asItemStack() != null) {
						items.add(value.asItemStack());
						itemChances.add(i < chances.length ? chances[i] : 1);
					} else if (value.asFluidStack() != null) {
						liquids.add(value.asFluidStack());
						liquidChances.add(i < chances.length ? chances[i] : 1);
					} else {
						throw new TweakerExecuteException("results must be items or liquid stacks");
					}
				}
			}
			
			TweakerItem[] validContainers = DEFAULT_VALID_CONTAINERS;
			if (arguments.length > 3 && arguments[3] != null) {
				if (arguments[3].asArray() != null) {
					TweakerArray validContainerArray = arguments[3].asArray();
					validContainers = new TweakerItem[validContainerArray.size()];
					for (int i = 0; i < validContainerArray.size(); i++) {
						validContainers[i] =
								notNull(validContainerArray.get(i), "valid container cannot be null")
								.toItem("valid container must be an item");
					}
				} else if (arguments[3].asItem() != null) {
					validContainers = new TweakerItem[] { arguments[3].asItem() };
				}
			}
			
			TweakerItemStack[] items2 = items.toArray(new TweakerItemStack[items.size()]);
			float[] itemChances2 = new float[itemChances.size()];
			for (int i = 0; i < itemChances.size(); i++) {
				itemChances2[i] = itemChances.get(i);
			}
			TweakerLiquidStack[] liquids2 = liquids.toArray(new TweakerLiquidStack[liquids.size()]);
			float[] liquidChances2 = new float[liquidChances.size()];
			for (int i = 0; i < liquidChances.size(); i++) {
				liquidChances2[i] = liquidChances.get(i);
			}
			
			Tweaker.apply(new RancherAddRanchableAction(clazz, items2, itemChances2, liquids2, liquidChances2, validContainers));
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("class not found: " + arguments[0].toBasicString());
		}
	}

	@Override
	public String toString() {
		return "rancher.addRanchable";
	}
}
