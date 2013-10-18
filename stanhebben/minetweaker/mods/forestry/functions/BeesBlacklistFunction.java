/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.BeesBlacklistAction;

/**
 *
 * @author Stanneke
 */
public class BeesBlacklistFunction extends TweakerFunction {
	public static final BeesBlacklistFunction INSTANCE = new BeesBlacklistFunction();
	
	private BeesBlacklistFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) throw new TweakerExecuteException("bees.blacklist requires 1 argument");
		String gene =
				notNull(arguments[0], "gene cannot be null")
				.toBasicString();
		
		Tweaker.apply(new BeesBlacklistAction(gene));
		return null;
	}

	@Override
	public String toString() {
		return "bees.blacklist";
	}
}
