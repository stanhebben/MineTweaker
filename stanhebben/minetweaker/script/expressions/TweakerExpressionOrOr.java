package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionOrOr extends TweakerExpression {
	private TweakerExpression a;
	private TweakerExpression b;
	
	public TweakerExpressionOrOr(TweakerFile file, int line, int offset, TweakerExpression a, TweakerExpression b) {
		super(file, line, offset);
		
		this.a = a;
		this.b = b;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue left = a.execute(namespace);
		if (left == null) throw new TweakerExecuteException("Cannot || a null value");
		if (left.asBool() == null) throw new TweakerExecuteException("Cannot convert the first side to a bool");
		if (left.asBool().get()) {
			return left;
		} else {
			TweakerValue right = b.execute(namespace);
			if (right == null) throw new TweakerExecuteException("Cannot || a null value");
			if (right.asBool() == null) throw new TweakerExecuteException("Cannot convert the right side to a bool");
			return right;
		}
	}
}
