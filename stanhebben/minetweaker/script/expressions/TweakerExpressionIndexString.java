package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionIndexString extends TweakerExpression {
	private TweakerExpression base;
	private String index;
	
	public TweakerExpressionIndexString(TweakerFile file, int line, int offset, TweakerExpression base, String index) {
		super(file, line, offset);
		
		this.base = base;
		this.index = index;
	}
	
	@Override
	public TweakerExpression assign(TweakerFile file, int line, int offset, TweakerExpression src) {
		return new TweakerExpressionIndexSetString(file, line, offset, base, index, src);
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue baseValue = base.execute(namespace);
		if (baseValue == null) throw new TweakerExecuteException("Cannot index a null value");
		return baseValue.index(index);
	}
}
