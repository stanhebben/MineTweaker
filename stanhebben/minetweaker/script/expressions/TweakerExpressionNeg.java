package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionNeg extends TweakerExpression {
	private TweakerExpression base;
	
	public TweakerExpressionNeg(TweakerFile file, int line, int offset, TweakerExpression base) {
		super(file, line, offset);
		
		this.base = base;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue baseValue = base.execute(namespace);
		if (baseValue == null) throw new TweakerExecuteException("Cannot negate a null value");
		return baseValue.neg();
	}
}
