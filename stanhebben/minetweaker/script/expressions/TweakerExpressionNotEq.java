package stanhebben.minetweaker.script.expressions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionNotEq extends TweakerExpression {
	private TweakerExpression a;
	private TweakerExpression b;
	
	public TweakerExpressionNotEq(TweakerFile file, int line, int offset, TweakerExpression a, TweakerExpression b) {
		super(file, line, offset);
		
		this.a = a;
		this.b = b;
	}

	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		TweakerValue va = a.execute(namespace);
		TweakerValue vb = b.execute(namespace);
		if (va == vb) {
			return TweakerBool.FALSE;
		} else if (va == null || vb == null) {
			return TweakerBool.TRUE;
		} else {
			return TweakerBool.get(!va.equals(vb));
		}
	}
}
