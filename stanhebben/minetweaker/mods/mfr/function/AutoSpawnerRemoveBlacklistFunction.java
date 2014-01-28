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
import stanhebben.minetweaker.mods.mfr.action.AutoSpawnerRemoveBlacklistAction;

/**
 *
 * @author Stanneke
 */
public class AutoSpawnerRemoveBlacklistFunction extends TweakerFunction {
	public static final AutoSpawnerRemoveBlacklistFunction INSTANCE = new AutoSpawnerRemoveBlacklistFunction();
	
	private AutoSpawnerRemoveBlacklistFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("autoSpawner.removeBlacklist requires 1 argument");
		try {
			Class<?> entityClass = Class.forName(
					notNull(arguments[0], "class cannot be null").toBasicString());
			//#ifdef MC152
			//+if (!EntityLiving.class.isAssignableFrom(entityClass)) {
			//#else
			if (!EntityLivingBase.class.isAssignableFrom(entityClass)) {
			//#endif
				throw new TweakerExecuteException("the class does not extend EntityLivingBase");
			} else if (MFRHacks.grindableBlacklist == null) {
				Tweaker.log(Level.WARNING, "autoSpawner.removeBlacklist is unavailable");
			} else {
				Tweaker.apply(new AutoSpawnerRemoveBlacklistAction(entityClass));
			}
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("entity class not found: " + arguments[0].toBasicString());
		}
	}

	@Override
	public String toString() {
		return "autoSpawner.removeGrindable";
	}
}
