/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

/**
 *
 * @author Stanneke
 */
public class TweakerExpressionIn extends TweakerExpression {
	private TweakerExpression value;
	private TweakerExpression key;
	
	public TweakerExpressionIn(
			TweakerFile file, int line, int offset,
			TweakerExpression value, TweakerExpression key) {
		super(file, line, offset);
		
		this.value = value;
		this.key = key;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		if (value == null) throw new TweakerExecuteException("cannot use null as in array");
		TweakerValue valueValue = value.execute(namespace);
		TweakerValue keyValue = key == null ? null : key.execute(namespace);
		return TweakerBool.get(valueValue.contains(keyValue));
	}
}
