package stanhebben.minetweaker.api.value;

import java.util.Iterator;

import net.minecraft.nbt.NBTBase;

public class TweakerRange extends TweakerValue {
	private int from;
	private int to;
	
	public TweakerRange(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public Iterator<TweakerValue> iterator() {
		return new Iterator<TweakerValue>() {
			int current = from;

			@Override
			public boolean hasNext() {
				return current < to;
			}

			@Override
			public TweakerValue next() {
				return new TweakerInt(from++);
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
		return from + ".." + to;
	}
}
