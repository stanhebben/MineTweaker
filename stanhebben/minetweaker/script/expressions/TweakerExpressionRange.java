/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerRange;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

/**
 *
 * @author Stanneke
 */
public class TweakerExpressionRange extends TweakerExpression {
	private TweakerExpression from;
	private TweakerExpression to;

	public TweakerExpressionRange(TweakerFile file, int line, int offset,
			TweakerExpression from, TweakerExpression to) {
		super(file, line, offset);
		
		this.from = from;
		this.to = to;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue fromValue = from.execute(namespace);
		TweakerValue toValue = to.execute(namespace);
		
		if (fromValue == null) throw new TweakerExecuteException("Cannot use null as start of a range");
		if (toValue == null) throw new TweakerExecuteException("Cannot use null as end of a range");
		
		int fromInt = fromValue.toInt("Start of a range expression must be an int").get();
		int toInt = toValue.toInt("End of a range expression must be an int").get();
		
		return new TweakerRange(fromInt, toInt);
	}
}
