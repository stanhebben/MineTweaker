package stanhebben.minetweaker.api.value;

import java.util.Iterator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import stanhebben.minetweaker.api.TweakerExecuteException;

/**
 * Wraps an NBT Tag List as array. Converts its contents to TweakerValues as
 * they are read.
 * 
 * @author Stan Hebben
 */
public class TweakerArrayNBT extends TweakerArray {
	private NBTTagList data;
	
	public TweakerArrayNBT(NBTTagList data) {
		this.data = data;
	}

	@Override
	public int size() {
		return data.tagCount();
	}

	@Override
	public TweakerValue get(int index) {
		return TweakerValue.fromNBT(data.tagAt(index));
	}

	@Override
	public TweakerValue remove(int index) {
		return TweakerValue.fromNBT(data.removeTag(index));
	}

	@Override
	public TweakerValue addAssign(TweakerValue value) {
		if (value == null) throw new TweakerExecuteException("Cannot add null to an NBT list");
		data.appendTag(value.toTagValue(null));
		return this;
	}

	@Override
	public TweakerValue subAssign(TweakerValue value) {
		if (value == null) throw new TweakerExecuteException("Cannot remove null from an NBT list");
		NBTBase tag = value.toTag(null);
		for (int i = 0; i < data.tagCount(); i++) {
			if (data.tagAt(i).equals(tag)) {
				data.removeTag(i);
				return this;
			}
		}
		return this;
	}

	@Override
	public Iterator<TweakerValue> iterator() {
		return new Iterator<TweakerValue>() {
			int i = 0;

			@Override
			public boolean hasNext() {
				return i < data.tagCount();
			}

			@Override
			public TweakerValue next() {
				return TweakerValue.fromNBT(data.tagAt(i));
			}

			@Override
			public void remove() {
				i--;
				data.removeTag(i);
			}
		};
	}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("copy")) {
			return new TweakerArrayNBT((NBTTagList) data.copy());
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public NBTBase toTagValue(String name) {
		if ((data.getName() == null && name == null) || data.getName().equals(name)) {
			return data;
		} else {
			return data.copy().setName(name);
		}
	}
}
