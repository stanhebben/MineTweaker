package stanhebben.minetweaker.script.statements;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.expressions.TweakerExpression;

public class StatementIf extends TweakerStatement {
	private TweakerExpression condition;
	private TweakerStatement onThen;
	private TweakerStatement onElse;
	
	public StatementIf(TweakerFile file, int line, int offset, TweakerExpression condition, TweakerStatement onThen, TweakerStatement onElse) {
		super(file, line, offset);
		
		this.condition = condition;
		this.onThen = onThen;
		this.onElse = onElse;
	}

	@Override
	public TweakerValue execute(TweakerNameSpace namespace)
			throws TweakerException {
		try {
			TweakerBool dir = condition.execute(namespace).asBool();
			if (dir == null) {
				throw new TweakerException(getFile(), getLine(), getOffset(), "Condition must be a bool value");
			}
			
			namespace = new TweakerNameSpace(namespace);
			if (dir.get()) {
				return onThen.execute(namespace);
			} else if (onElse != null) {
				return onElse.execute(namespace);
			} else {
				return null;
			}
		} catch (TweakerExecuteException ex) {
			throw new TweakerException(getFile(), getLine(), getOffset(), ex.getMessage());
		}
	}
}
