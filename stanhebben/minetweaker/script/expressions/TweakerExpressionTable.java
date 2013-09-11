package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerTable;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionTable extends TweakerExpression {
	private TweakerExpression[] keys;
	private TweakerExpression[] values;
	
	public TweakerExpressionTable(
			TweakerFile file, int line, int offset,
			TweakerExpression[] keys, TweakerExpression[] values) {
		super(file, line, offset);
		
		this.keys = keys;
		this.values = values;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerTable result = new TweakerTable();
		for (int i = 0; i < keys.length; i++) {
			result.indexSet(keys[i].execute(namespace), values[i].execute(namespace));
		}
		return result;
	}
}
