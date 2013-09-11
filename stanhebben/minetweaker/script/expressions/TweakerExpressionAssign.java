package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionAssign extends TweakerExpression {
	private TweakerExpression left;
	private TweakerExpression right;
	
	public TweakerExpressionAssign(TweakerFile file, int line, int offset, TweakerExpression left, TweakerExpression right) {
		super(file, line, offset);
		
		this.left = left;
		this.right = right;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue result = right.execute(namespace);
		left.assign(namespace, result);
		return result;
	}
}
