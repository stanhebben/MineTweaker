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
public class FunctionItemRemoveTool extends TweakerFunction {
	private final TweakerItem item;
	
	public FunctionItemRemoveTool(TweakerItem item) {
		this.item = item;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("item.removeTool requires 1 argument");
		
		String toolClass =
				notNull(arguments[0], "tool class cannot be null").toBasicString();
		item.removeTool(toolClass);
		return null;
	}

	@Override
	public String toString() {
		return "item.removeTool";
	}
}
