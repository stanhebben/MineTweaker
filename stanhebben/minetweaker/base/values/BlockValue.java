package stanhebben.minetweaker.base.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.api.value.TweakerLiquid;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;

public class BlockValue extends TweakerValue {
	private TweakerItem item;
	
	public BlockValue(TweakerItem item) {
		this.item = item;
	}
	
	public String getName() {
		return item.getName();
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) {
		return item.mul(value);
	}
	
	@Override
	public TweakerItem asItem() {
		return item;
	}
	
	@Override
	public TweakerItemPattern asItemPattern() {
		return item.asItemPattern();
	}
	
	@Override
	public TweakerItemStack asItemStack() {
		return item.asItemStack();
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return item.asItemStackPattern();
	}
	
	@Override
	public TweakerLiquid asFluid() {
		return item.asFluid();
	}
	
	@Override
	public TweakerLiquidStack asFluidStack() {
		return item.asFluidStack();
	}
	
	@Override
	public Object asRecipeItem() {
		return item.asRecipeItem();
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		return item.index(index);
	}
	
	@Override
	public void indexSet(String index, TweakerValue value) throws TweakerExecuteException {
		item.indexSet(index, value);
	}

	@Override
	public String toString() {
		return item.getName();
	}
}
