package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionCall extends TweakerExpression {
	private TweakerExpression base;
	private TweakerExpression[] arguments;
	
	public TweakerExpressionCall(TweakerFile file, int line, int offset, TweakerExpression base, TweakerExpression[] arguments) {
		super(file, line, offset);
		
		this.base = base;
		this.arguments = arguments;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue baseValue = base.execute(namespace);
		if (baseValue == null) throw new TweakerExecuteException("Cannot call a null value");
		
		TweakerValue[] evalArguments = new TweakerValue[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			evalArguments[i] = arguments[i].execute(namespace);
		}
		return baseValue.call(namespace, evalArguments);
	}
}
