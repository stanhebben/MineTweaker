package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.SetAdminsAction;

/**
 * Implements minetweaker.setAdmins .
 * 
 * @author Stan Hebben
 */
public class SetAdminsFunction extends TweakerFunction {
	public static final SetAdminsFunction INSTANCE = new SetAdminsFunction();
	
	private SetAdminsFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		String[] admins = new String[arguments.length];
		for (int i = 0; i < admins.length; i++) {
			admins[i] =
					notNull(arguments[i], "admin username cannot be null")
					.toBasicString();
		}
		Tweaker.apply(new SetAdminsAction(admins));
		return null;
	}

	@Override
	public String toString() {
		return "minetweaker.setAdmins";
	}
}
