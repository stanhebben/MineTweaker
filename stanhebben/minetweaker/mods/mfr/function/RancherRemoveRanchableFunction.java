/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.logging.Level;
import net.minecraft.entity.EntityLivingBase;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.RancherRemoveRanchableAction;

/**
 *
 * @author Stanneke
 */
public class RancherRemoveRanchableFunction extends TweakerFunction {
	public static final RancherRemoveRanchableFunction INSTANCE = new RancherRemoveRanchableFunction();
	
	private RancherRemoveRanchableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		try {
			if (arguments.length != 1) throw new TweakerExecuteException("rancher.removeRanchable requires 1 argument");
			Class<?> cls = Class.forName(notNull(arguments[0], "class cannot be null").toBasicString());
			if (!EntityLivingBase.class.isAssignableFrom(cls)) {
				throw new TweakerExecuteException("class is not a living entity class");
			}
			
			if (MFRHacks.ranchables == null) {
				Tweaker.log(Level.WARNING, "rancher.removeRanchable is unavailable");
			} else if (!MFRHacks.ranchables.containsKey(cls)) {
				Tweaker.log(Level.WARNING, "no such ranchable: " + cls.getCanonicalName());
			} else {
				Tweaker.apply(new RancherRemoveRanchableAction(cls));
			}
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("could not find class " + arguments[0].toBasicString());
		}
	}

	@Override
	public String toString() {
		return "rancher.removeRanchable";
	}
}
