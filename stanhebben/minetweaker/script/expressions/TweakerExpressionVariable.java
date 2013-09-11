package stanhebben.minetweaker.script.expressions;

import net.minecraft.item.Item;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;

public class TweakerExpressionVariable extends TweakerExpression {
	private String name;
	
	public TweakerExpressionVariable(TweakerFile file, int line, int offset, String name) {
		super(file, line, offset);
		
		this.name = name;
	}
	
	@Override
	public TweakerValue executeInner(TweakerNameSpace namespace) {
		if (namespace.contains(name)) {
			return namespace.get(name);
		} else {
			// check if it exists as item name start
			for (Item item : Item.itemsList) {
				if (item == null) continue;
				if (item.getUnlocalizedName() == null) continue;
				if (item.getUnlocalizedName().startsWith(name)) {
					throw new TweakerExecuteException("Symbol " + name + " not found. Did you intend to use items." + name + "?");
				}
			}
			throw new TweakerExecuteException("Symbol " + name + " not found.");
		}
	}
	
	@Override
	public void assign(TweakerNameSpace namespace, TweakerValue value) {
		namespace.put(name, value);
	}
}
