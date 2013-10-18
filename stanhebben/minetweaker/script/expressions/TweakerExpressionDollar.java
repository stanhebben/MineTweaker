/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

/**
 *
 * @author Stanneke
 */
public class TweakerExpressionDollar extends TweakerExpression {;
	public TweakerExpressionDollar(TweakerFile file, int line, int offset) {
		super(file, line, offset);
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		return namespace.get("$");
	}
}
