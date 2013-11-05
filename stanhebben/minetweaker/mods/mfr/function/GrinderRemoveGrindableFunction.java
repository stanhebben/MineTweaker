/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.GrinderRemoveGrindableAction;

/**
 *
 * @author Stanneke
 */
public class GrinderRemoveGrindableFunction extends TweakerFunction {
	public static final GrinderRemoveGrindableFunction INSTANCE = new GrinderRemoveGrindableFunction();
	
	private GrinderRemoveGrindableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("grinder.removeGrindable requires 1 argument");
		try {
			Class<?> entityClass = Class.forName(
					notNull(arguments[0], "entity class cannot be null")
							.toBasicString());
			if (MFRHacks.grindables == null) {
				Tweaker.log(Level.WARNING, "grinder.removeGrindable is unavailable");
			} else if (!MFRHacks.grindables.containsKey(entityClass)) {
				Tweaker.log(Level.WARNING, "entity " + entityClass.getCanonicalName() + " is not a grindable entity");
			} else {
				Tweaker.apply(new GrinderRemoveGrindableAction(entityClass));
			}
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("could not find entity class " + arguments[0].toBasicString());
		}
	}

	@Override
	public String toString() {
		return "grinder.removeGrindable";
	}
}
