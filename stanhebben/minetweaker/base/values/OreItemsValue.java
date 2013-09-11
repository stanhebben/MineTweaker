package stanhebben.minetweaker.base.values;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraftforge.oredict.OreDictionary;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemSimple;
import stanhebben.minetweaker.api.value.TweakerItemSub;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.AddOreItemAction;
import stanhebben.minetweaker.base.actions.RemoveOreItemAction;

public class OreItemsValue extends TweakerArray {
	private String ore;
	ArrayList<ItemStack> ores;
	
	public OreItemsValue(String value) {
		this.ore = value;
		ores = OreDictionary.getOres(value);
	}
	
	@Override
	public int size() {
		return ores.size();
	}
	
	@Override
	public TweakerValue get(int index) throws TweakerExecuteException {
		if (index >= ores.size()) {
			throw new TweakerExecuteException("index out of bounds for ore list: " + index);
		}
		ItemStack value = ores.get(index);
		if (value.getHasSubtypes() && value.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
			return new TweakerItemSub(value.itemID, value.getItemDamage());
		} else {
			return new TweakerItemSimple(value.itemID);
		}
	}
	
	@Override
	public TweakerValue remove(int index) throws TweakerExecuteException {
		TweakerValue result = get(index);
		Tweaker.apply(new RemoveOreItemAction(ore, index));
		return result;
	}
	
	@Override
	public TweakerValue addAssign(TweakerValue value) throws TweakerExecuteException {
		Tweaker.apply(new AddOreItemAction(
				ore, 
				notNull(value, "cannot add null to the ore items list")
					.toItem("can only add items to the ore items list")
					.make(1)));
		return this;
	}
	
	@Override
	public TweakerValue subAssign(TweakerValue value) throws TweakerExecuteException {
		TweakerItemPattern pattern = 
				notNull(value, "cannot remove null values")
				.toItemPattern("can only remove items and item patterns from the ore list");
		int numRemoved = 0;
		for (int i = ores.size() - 1; i >= 0; i--) {
			if (pattern.matches(ores.get(i))) {
				Tweaker.apply(new RemoveOreItemAction(ore, i));
				numRemoved++;
			}
		}
		return new TweakerInt(numRemoved);
	}
	
	@Override
	public Iterator<TweakerValue> iterator() {
		return new Iterator<TweakerValue>() {
			private Iterator<ItemStack> base = ores.iterator();
			
			@Override
			public boolean hasNext() {
				return base.hasNext();
			}

			@Override
			public TweakerValue next() {
				try {
					ItemStack value = base.next();
					if (value.getHasSubtypes() && value.getItemDamage() != OreDictionary.WILDCARD_VALUE) {
						return new TweakerItemSub(value.itemID, value.getItemDamage());
					} else {
						return new TweakerItemSimple(value.itemID);
					}
				} catch (TweakerExecuteException ex) {
					throw new RuntimeException(ex); // not supposed to happen
				}
			}

			@Override
			public void remove() {}
		};
	}

	@Override
	public NBTBase toTagValue(String name) {
		return null;
	}

	@Override
	public String toString() {
		return "OreItems:" + ore;
	}
}
