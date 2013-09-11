package stanhebben.minetweaker.api.value;

import java.util.Arrays;
import java.util.Iterator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import stanhebben.minetweaker.api.TweakerExecuteException;

/**
 * Implements an int array. Converts to an int array NBT tag, is generated when
 * reading an int array NBT tag, and is the conversion result of <item> as int[]
 * expression.
 * 
 * @author Stan Hebben
 */
public class TweakerArrayInts extends TweakerArray {
	private int[] data;
	private int size;
	
	public TweakerArrayInts() {
		data = new int[16];
		size = 0;
	}
	
	public TweakerArrayInts(int[] data) {
		size = data.length;
		if (data.length < 16) data = Arrays.copyOf(data, 16);
		this.data = data;
	}
	
	public void addInt(int value) {
		if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
		data[size++] = value;
	}
	
	public void addInt(int index, int value) {
		if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = value;
	}
	
	public int getInt(int index) {
		return data[index];
	}
	
	public int removeInt(int index) {
		int result = data[index];
		size--;
		for (int i = index; i < size; i++) {
			data[i] = data[i + 1];
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public TweakerValue get(int index) {
		return new TweakerInt(data[index]);
	}

	@Override
	public TweakerValue remove(int index) {
		return new TweakerInt(removeInt(index));
	}

	@Override
	public TweakerValue addAssign(TweakerValue value) {
		if (value == null) throw new TweakerExecuteException("Cannot convert a null value to an int");
		addInt(value.toInt("cannot convert this value to int").get());
		return this;
	}

	@Override
	public TweakerValue subAssign(TweakerValue value) {
		if (value == null) throw new TweakerExecuteException("Cannot convert a null value to an int");
		int ivalue = value.toInt("cannot convert this value to int").get();
		for (int i = 0; i < size; i++) {
			if (data[i] == ivalue) {
				removeInt(i);
				return this;
			}
		}
		return this;
	}

	@Override
	public Iterator<TweakerValue> iterator() {
		return new Iterator<TweakerValue>() {
			private int i;
			
			@Override
			public boolean hasNext() {
				return i < size;
			}

			@Override
			public TweakerValue next() {
				return new TweakerInt(data[i++]);
			}

			@Override
			public void remove() {
				i--;
				removeInt(i);
			}
		};
	}
	
	@Override
	public TweakerArrayBytes asByteArray() {
		byte[] data = new byte[size];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) this.data[i];
		}
		return new TweakerArrayBytes(data);
	}
	
	@Override
	public TweakerArrayInts asIntArray() {
		return this;
	}
	
	@Override
	public TweakerValue index(String index) {
		if (index == null) {
			throw new TweakerExecuteException("Cannot index an int array with a null value");
		} else if (index.equals("copy")) {
			return new TweakerArrayInts(Arrays.copyOf(data, size));
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public NBTBase toTagValue(String name) {
		return new NBTTagIntArray(name, size < data.length ? Arrays.copyOf(data, size) : data);
	}
}
