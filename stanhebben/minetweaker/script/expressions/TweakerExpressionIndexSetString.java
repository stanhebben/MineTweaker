package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionIndexSetString extends TweakerExpression {
	private TweakerExpression src;
	private String index;
	private TweakerExpression value;
	
	public TweakerExpressionIndexSetString(TweakerFile file, int line, int offset,
			TweakerExpression src, String index, TweakerExpression value) {
		super(file, line, offset);
		
		this.src = src;
		this.index = index;
		this.value = value;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue srcValue = src.execute(namespace);
		if (srcValue == null) throw new TweakerExecuteException("Cannot perform an indexed assignment to a null value");
		
		TweakerValue tvalue = value.execute(namespace);
		src.execute(namespace).indexSet(index, tvalue);
		return tvalue;
	}
}
