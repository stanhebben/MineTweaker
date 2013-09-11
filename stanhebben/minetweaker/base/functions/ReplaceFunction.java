/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class ReplaceFunction extends TweakerFunction {
	public static final ReplaceFunction INSTANCE = new ReplaceFunction();
	
	private ReplaceFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("minetweaker.replace requires two arguments");
		}
		TweakerItemPattern original =
				notNull(arguments[0], "minetweaker.replace original argument cannot be null")
				.toItemPattern("minetweaker.replace original argument must be item or item pattern");
		TweakerValue replaced =
				notNull(arguments[0], "minetweaker.replace replaced argument cannot be null");
		if (replaced.asRecipeItem() == null) {
			throw new TweakerExecuteException("minetweake.replace replaced argument must be a valid recipe item");
		}
		return new TweakerInt(MineTweaker.instance.replace(original, replaced));
	}

	@Override
	public String toString() {
		return "minetweaker.replace";
	}
}
