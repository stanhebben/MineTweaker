package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerItemSimple;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionItemId extends TweakerExpression {
	private int id;
	
	public TweakerExpressionItemId(TweakerFile file, int line, int offset, int id) {
		super(file, line, offset);
		
		this.id = id;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		return new TweakerItemSimple(id);
	}
}
