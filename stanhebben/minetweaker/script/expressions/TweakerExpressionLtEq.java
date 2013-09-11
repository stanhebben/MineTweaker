package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionLtEq extends TweakerExpression {
	private TweakerExpression a;
	private TweakerExpression b;
	
	public TweakerExpressionLtEq(TweakerFile file, int line, int offset, TweakerExpression a, TweakerExpression b) {
		super(file, line, offset);
		
		this.a = a;
		this.b = b;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue aValue = a.execute(namespace);
		if (aValue == null) throw new TweakerExecuteException("Cannot compare with a null value");
		TweakerValue bValue = b.execute(namespace);
		if (bValue == null) throw new TweakerExecuteException("Cannot compare with a null value");
		return TweakerBool.get(aValue.compare(bValue) <= 0);
	}
}
