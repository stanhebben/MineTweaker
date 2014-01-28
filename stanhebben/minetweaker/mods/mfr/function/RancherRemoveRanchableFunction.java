/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.logging.Level;
//#ifdef MC152
//+import net.minecraft.entity.EntityLiving;
//#else
import net.minecraft.entity.EntityLivingBase;
//#endif
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
			Class<?> entityClass = Class.forName(notNull(arguments[0], "class cannot be null").toBasicString());
			//#ifdef MC152
			//+if (!EntityLiving.class.isAssignableFrom(entityClass)) {
			//#else
			if (!EntityLivingBase.class.isAssignableFrom(entityClass)) {
			//#endif
				throw new TweakerExecuteException("class is not a living entity class");
			}
			
			if (MFRHacks.ranchables == null) {
				Tweaker.log(Level.WARNING, "rancher.removeRanchable is unavailable");
			} else if (!MFRHacks.ranchables.containsKey(entityClass)) {
				Tweaker.log(Level.WARNING, "no such ranchable: " + entityClass.getCanonicalName());
			} else {
				Tweaker.apply(new RancherRemoveRanchableAction(entityClass));
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
