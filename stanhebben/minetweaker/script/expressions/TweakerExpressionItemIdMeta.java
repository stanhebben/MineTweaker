package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerItemSub;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionItemIdMeta extends TweakerExpression {
	private int id;
	private int meta;
	
	public TweakerExpressionItemIdMeta(TweakerFile file, int line, int offset, int id, int meta) {
		super(file, line, offset);
		
		this.id = id;
		this.meta = meta;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		return new TweakerItemSub(id, meta);
	}
}
