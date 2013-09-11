package stanhebben.minetweaker.api.value;

import java.util.Iterator;

import stanhebben.minetweaker.api.TweakerExecuteException;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

/**
 * Abstract superclass of array implementations. The standard generic implementation
 * is TweakerArrayValue, but other implementations can be defined as well.
 * 
 * @author Stan Hebben
 */
public abstract class TweakerArray extends TweakerValue {
	public TweakerArray() {}
	
	public abstract int size();
	
	public abstract TweakerValue get(int index);
	
	public abstract TweakerValue remove(int index);
	
	@Override
	public TweakerArray asArray() {
		return this;
	}
	
	@Override
	public TweakerArrayBytes asByteArray() {
		byte[] result = new byte[size()];
		for (int i = 0; i < result.length; i++) {
			TweakerByte asByte = get(i).asByte();
			if (asByte == null) return null;
			result[i] = asByte.get();
		}
		return new TweakerArrayBytes(result);
	}
	
	@Override
	public TweakerArrayInts asIntArray() {
		int[] result = new int[size()];
		for (int i = 0; i < result.length; i++) {
			TweakerInt asInt = get(i).asInt();
			if (asInt == null) return null;
			result[i] = asInt.get();
		}
		return new TweakerArrayInts(result);
	}
	
	@Override
	public abstract TweakerValue addAssign(TweakerValue value);
	
	@Override
	public abstract TweakerValue subAssign(TweakerValue value);

	@Override
	public TweakerValue index(TweakerValue value) {
		if (value == null) {
			throw new TweakerExecuteException("Cannot index an array with a null value");
		} else if (value.getClass() == TweakerString.class) {
			return index(value.asString());
		} else {
			return get(value.toBasicInt());
		}
	}
	
	@Override
	public TweakerValue index(int value) {
		return get(value);
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case BYTEARRAY:
				return asByteArray();
			case INTARRAY:
				return asIntArray();
			case LENGTH:
				return new TweakerInt(size());
			default:
				throw new TweakerExecuteException("on such member in array: " + index);
		}
	}
	
	@Override
	public abstract Iterator<TweakerValue> iterator();

	@Override
	public NBTBase toTagValue(String name) {
		NBTTagList list = new NBTTagList(name);
		Iterator<TweakerValue> iterator = iterator();
		while (iterator.hasNext()) {
			TweakerValue value = iterator.next();
			NBTBase tag = value.toTagValue(null);
			if (tag == null) {
				throw new TweakerExecuteException("Could not convert " + value + " to NBT value");
			}
			list.appendTag(tag);
		}
		return list;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append('[');
		boolean first = true;
		Iterator<TweakerValue> iterator = iterator();
		while (iterator.hasNext()) {
			TweakerValue value = iterator.next();
			if (first) {
				first = false;
			} else {
				result.append(", ");
			}
			result.append(value.toString());
		}
		result.append(']');
		return result.toString();
	}
}
