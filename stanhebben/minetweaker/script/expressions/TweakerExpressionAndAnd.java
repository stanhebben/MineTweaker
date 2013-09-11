package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionAndAnd extends TweakerExpression {
	private TweakerExpression a;
	private TweakerExpression b;
	
	public TweakerExpressionAndAnd(TweakerFile file, int line, int offset, TweakerExpression a, TweakerExpression b) {
		super(file, line, offset);
		
		this.a = a;
		this.b = b;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue left = a.execute(namespace);
		if (left == null) throw new TweakerExecuteException("left side of an && operation cannot be null");
		if (left.asBool() == null) throw new TweakerExecuteException("Cannot convert the left value to a bool");
		if (left.asBool().get()) {
			TweakerValue right = b.execute(namespace);
			if (right == null) throw new TweakerExecuteException("right side of an && operation cannot be null");
			if (right.asBool() == null) throw new TweakerExecuteException("Cannot convert the right value to a bool");
			return right;
		} else {
			return left;
		}
	}
}
