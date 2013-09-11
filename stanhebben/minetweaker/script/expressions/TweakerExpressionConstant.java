package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionConstant extends TweakerExpression {
	private TweakerValue value;
	
	public TweakerExpressionConstant(TweakerFile file, int line, int offset, TweakerValue value) {
		super(file, line, offset);
		
		this.value = value;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		return value;
	}
}
