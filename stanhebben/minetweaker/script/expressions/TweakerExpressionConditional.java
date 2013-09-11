package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionConditional extends TweakerExpression {
	private TweakerExpression condition;
	private TweakerExpression onIf;
	private TweakerExpression onElse;
	
	public TweakerExpressionConditional(
			TweakerFile file, int line, int offset,
			TweakerExpression condition, TweakerExpression onIf, TweakerExpression onElse) {
		super(file, line, offset);
		this.condition = condition;
		this.onIf = onIf;
		this.onElse = onElse;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue cond = condition.execute(namespace);
		if (cond == null) throw new TweakerExecuteException("Cannot convert a null value to a bool");
		if (cond.asBool() == null) throw new TweakerExecuteException("Could not convert the conditional value to a bool");
		
		if (cond.asBool().get()) {
			return onIf.execute(namespace);
		} else {
			return onElse.execute(namespace);
		}
	}
}
