/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.UndoBreakerAction;

/**
 *
 * @author Stanneke
 */
public class BreakUndoFunction extends TweakerFunction {
	public static final BreakUndoFunction INSTANCE = new BreakUndoFunction();
	
	private BreakUndoFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		Tweaker.apply(new UndoBreakerAction());
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:breakUndo";
	}
}
