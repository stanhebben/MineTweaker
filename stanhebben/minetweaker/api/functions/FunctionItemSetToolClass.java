/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class FunctionItemSetToolClass extends TweakerFunction {
	private final TweakerItem item;
	
	public FunctionItemSetToolClass(TweakerItem item) {
		this.item = item;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) throw new TweakerExecuteException("item.setToolClass requires 2 arguments");
		String toolClass =
				notNull(arguments[0], "tool class cannot be null")
				.toBasicString();
		int toolLevel =
				notNull(arguments[1], "tool level cannot be null")
				.toInt("tool level must be an int").get();
		
		item.setToolClass(toolClass, toolLevel);
		return null;
	}

	@Override
	public String toString() {
		return "item.setToolClass";
	}
}
