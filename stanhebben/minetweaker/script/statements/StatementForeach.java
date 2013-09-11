package stanhebben.minetweaker.script.statements;

import java.util.Iterator;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.expressions.TweakerExpression;

public class StatementForeach extends TweakerStatement {
	private String varname;
	private TweakerExpression list;
	private TweakerStatement body;
	
	public StatementForeach(TweakerFile file, int line, int offset, String varname, TweakerExpression list, TweakerStatement body) {
		super(file, line, offset);
	}

	@Override
	public TweakerValue execute(TweakerNameSpace namespace)
			throws TweakerException {
		TweakerValue listValue = list.execute(namespace);
		namespace = new TweakerNameSpace(namespace);
		
		try {
			Iterator<TweakerValue> values = listValue.iterator();
			if (values == null) throw new TweakerExecuteException("This value is not iterable");
			while (values.hasNext()) {
				namespace.put(varname, values.next());
				TweakerValue result = body.execute(namespace);
				if (result != null) return result;
			}
		} catch (TweakerExecuteException ex) {
			throw new TweakerException(getFile(), getLine(), getOffset(), ex.getMessage());
		}
		
		return null;
	}
}
