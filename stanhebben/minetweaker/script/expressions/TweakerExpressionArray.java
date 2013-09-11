package stanhebben.minetweaker.script.expressions;

import java.util.ArrayList;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionArray extends TweakerExpression {
	private TweakerExpression[] contents;
	
	public TweakerExpressionArray(TweakerFile file, int line, int offset, TweakerExpression[] contents) {
		super(file, line, offset);
		this.contents = contents;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		ArrayList<TweakerValue> contentValues = new ArrayList<TweakerValue>(contents.length);
		for (int i = 0; i < contents.length; i++) {
			contentValues.add(contents[i].execute(namespace));
		}
		return new TweakerArrayValue(contentValues);
	}
}
