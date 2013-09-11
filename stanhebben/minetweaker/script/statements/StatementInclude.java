package stanhebben.minetweaker.script.statements;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.expressions.TweakerExpression;

public class StatementInclude extends TweakerStatement {
	private TweakerExpression target;
	
	public StatementInclude(TweakerFile file, int line, int offset, TweakerExpression target) {
		super(file, line, offset);
		
		this.target = target;
	}

	@Override
	public TweakerValue execute(TweakerNameSpace namespace)
			throws TweakerException {
		String targetString = target.execute(namespace).toBasicString();
		TweakerFile file = getFile().findFile(targetString);
		if (file == null) {
			throw new TweakerException(getFile(), getLine(), getOffset(), targetString + " not found");
		} else {
			file.execute(namespace);
		}
		return null;
	}
}
