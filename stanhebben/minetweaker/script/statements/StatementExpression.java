package stanhebben.minetweaker.script.statements;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.expressions.TweakerExpression;

public class StatementExpression extends TweakerStatement {
	private TweakerExpression expression;
	
	public StatementExpression(TweakerFile file, int line, int offset, TweakerExpression expression) {
		super(file, line, offset);
		
		this.expression = expression;
	}

	@Override
	public TweakerValue execute(TweakerNameSpace namespace)
			throws TweakerException {
		expression.execute(namespace);
		return null;
	}
}
