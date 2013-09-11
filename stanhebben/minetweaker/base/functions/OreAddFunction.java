package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemPatternAnyMeta;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.AddOreItemAction;
import stanhebben.minetweaker.base.actions.AddOreItemWildcardAction;

public class OreAddFunction extends TweakerFunction {
	private String ore;
	
	public OreAddFunction(String ore) {
		this.ore = ore;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("ore.add requires one argument");
		} else {
			if (arguments[0] == null) {
				throw new TweakerExecuteException("ore.add argument must not be null");
			}
			
			if (arguments[0].getClass() == TweakerItemPatternAnyMeta.class) {
				Tweaker.apply(new AddOreItemWildcardAction(ore, ((TweakerItemPatternAnyMeta) arguments[0]).getId()));
			} else {
				TweakerItem item = arguments[0].asItem();
				if (item == null) {
					throw new TweakerExecuteException("ore.add argument must be an item");
				}
				Tweaker.apply(new AddOreItemAction(ore, item.make(1)));
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
