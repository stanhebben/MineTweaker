package stanhebben.minetweaker.script.statements;

import java.util.List;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class StatementBlock extends TweakerStatement {
	private List<TweakerStatement> statements;
	
	public StatementBlock(TweakerFile file, int line, int offset, List<TweakerStatement> statements) {
		super(file, line, offset);
		
		this.statements = statements;
	}
	
	@Override
	public TweakerValue execute(TweakerNameSpace namespace) {
		for (TweakerStatement statement : statements) {
			TweakerValue result = statement.execute(namespace);
			if (result != null) return result;
		}
		return null;
	}

}
