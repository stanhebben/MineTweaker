package stanhebben.minetweaker.base.functions;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.RemoveOreItemAction;

public class OreRemoveFunction extends TweakerFunction {
	private String ore;
	
	public OreRemoveFunction(String ore) {
		this.ore = ore;
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("ore.remove requires one argument");
		} else {
			if (arguments[0] == null) {
				throw new TweakerExecuteException("the ore.remove argument must not be null");
			}
			TweakerItemPattern pattern = arguments[0].asItemPattern();
			if (pattern == null) {
				throw new TweakerExecuteException("the ore.remove argument must be an item or item pattern");
			}
			List<ItemStack> items = OreDictionary.getOres(ore);
			int numRemoved = 0;
			for (int i = items.size() - 1; i >= 0; i--) {
				if (pattern.matches(items.get(i))) {
					Tweaker.apply(new RemoveOreItemAction(ore, i));
					numRemoved++;
				}
			}
			return new TweakerInt(numRemoved);
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:ore.remove";
	}
}
