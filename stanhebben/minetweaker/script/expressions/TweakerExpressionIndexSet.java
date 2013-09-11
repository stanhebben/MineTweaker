package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionIndexSet extends TweakerExpression {
	private TweakerExpression src;
	private TweakerExpression index;
	private TweakerExpression value;
	
	public TweakerExpressionIndexSet(TweakerFile file, int line, int offset,
			TweakerExpression src, TweakerExpression index, TweakerExpression value) {
		super(file, line, offset);
		
		this.src = src;
		this.index = index;
		this.value = value;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue tvalue = value.execute(namespace);
		TweakerValue srcValue = src.execute(namespace);
		if (srcValue == null) throw new TweakerExecuteException("Cannot perform an indexed assignment on a null value");
		srcValue.indexSet(index.execute(namespace), tvalue);
		return tvalue;
	}
}
