/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

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
import stanhebben.minetweaker.mods.mfr.action.SafariNetRemoveBlacklistAction;

/**
 *
 * @author Stanneke
 */
public class SafariNetRemoveBlacklistFunction extends TweakerFunction {
	public static final SafariNetRemoveBlacklistFunction INSTANCE = new SafariNetRemoveBlacklistFunction();
	
	private SafariNetRemoveBlacklistFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("safariNet.removeBlacklist requires 1 argument");
		try {
			Class<?> entityClass = Class.forName(notNull(arguments[0], "entity class cannot be null").toBasicString());
			//#ifdef MC152
			//+if (!EntityLiving.class.isAssignableFrom(entityClass)) {
			//#else
			if (!EntityLivingBase.class.isAssignableFrom(entityClass)) {
			//#endif
				throw new TweakerExecuteException("class is not a living entity class");
			}
			Tweaker.apply(new SafariNetRemoveBlacklistAction(entityClass));
			return null;
		} catch (ClassNotFoundException ex) {
			throw new TweakerExecuteException("entity class not found: " + arguments[0].toBasicString());
		}
	}

	@Override
	public String toString() {
		return "safariNet.removeBlacklist";
	}
}
