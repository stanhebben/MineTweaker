package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionInvert extends TweakerExpression {
	private TweakerExpression base;
	
	public TweakerExpressionInvert(TweakerFile file, int line, int offset, TweakerExpression base) {
		super(file, line, offset);
		
		this.base = base;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue baseValue = base.execute(namespace);
		if (baseValue == null) throw new TweakerExecuteException("Cannot invert a null value");
		return base.execute(namespace).not();
	}
}
