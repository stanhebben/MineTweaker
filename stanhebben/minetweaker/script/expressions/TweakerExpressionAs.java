package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.types.TweakerType;

public class TweakerExpressionAs extends TweakerExpression {
	private TweakerExpression value;
	private TweakerType type;
	
	public TweakerExpressionAs(TweakerFile file, int line, int offset, 
			TweakerExpression value, TweakerType type) {
		super(file, line, offset);
		
		this.value = value;
		this.type = type;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		return type.as(value.executeInner(namespace));
	}
}
