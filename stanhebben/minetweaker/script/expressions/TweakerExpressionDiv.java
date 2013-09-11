package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionDiv extends TweakerExpression {
	private TweakerExpression a;
	private TweakerExpression b;
	
	public TweakerExpressionDiv(TweakerFile file, int line, int offset, TweakerExpression a, TweakerExpression b) {
		super(file, line, offset);
		
		this.a = a;
		this.b = b;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue aValue = a.execute(namespace);
		if (aValue == null) throw new TweakerExecuteException("Cannot divide a null value");
		return aValue.div(b.execute(namespace));
	}
}
