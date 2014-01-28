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
public class FunctionItemAddTool extends TweakerFunction {
	private final TweakerItem item;
	
	public FunctionItemAddTool(TweakerItem item) {
		this.item = item;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 1 || arguments.length > 2) throw new TweakerExecuteException("item.setTool requires 1-2 arguments");
		String toolClass = notNull(arguments[0], "tool class cannot be null")
				.toBasicString();
		int level = arguments.length < 2 || arguments[1] == null ? 0 :
				arguments[1].toInt("tool level must be an int").get();
		
		item.addTool(toolClass, level);
		return null;
	}

	@Override
	public String toString() {
		return "item.setTool";
	}
}
