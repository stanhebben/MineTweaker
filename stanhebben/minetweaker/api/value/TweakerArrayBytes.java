package stanhebben.minetweaker.api.value;

import java.util.Arrays;
import java.util.Iterator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByteArray;
import stanhebben.minetweaker.api.TweakerExecuteException;

/**
 * Implements a byte array. Converts to a byte array NBT tag, is generated when
 * reading a byte array NBT tag, and is the conversion result of <item> as byte[]
 * expression.
 * 
 * @author Stan Hebben
 */
public class TweakerArrayBytes extends TweakerArray {
	private byte[] data;
	private int size;
	
	public TweakerArrayBytes() {
		data = new byte[16];
		size = 0;
	}
	
	public TweakerArrayBytes(byte[] data) {
		size = data.length;
		if (data.length < 16) data = Arrays.copyOf(data, 16);
		this.data = data;
	}
	
	public int size() {
		return size;
	}
	
	public byte getByte(int index) {
		return data[index];
	}
	
	public void addByte(byte value) {
		if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
		data[size++] = value;
	}
	
	public void addByte(int index, byte value) {
		if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = value;
		size++;
	}
	
	public byte removeByte(int index) {
		byte result = data[index];
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return result;
	}
	
	@Override
	public TweakerValue get(int index) {
		return new TweakerByte(data[index]);
	}
	
	@Override
	public TweakerValue remove(int index) {
		return new TweakerByte(removeByte(index));
	}

	@Override
	public TweakerValue addAssign(TweakerValue value) {
		if (value == null) throw new TweakerExecuteException("Cannot convert null to a byte value");
		addByte(value.toByte("value cannot be converted to a byte").get());
		return this;
	}

	@Override
	public TweakerValue subAssign(TweakerValue value) {
		if (value == null) throw new TweakerExecuteException("Cannot convert null to a byte value");
		byte bvalue = value.toByte("value cannot be converted to a byte").get();
		for (int i = 0; i < size; i++) {
			if (data[i] == bvalue) {
				removeByte(i);
				return this;
			}
		}
		return this;
	}

	@Override
	public Iterator<TweakerValue> iterator() {
		return new Iterator<TweakerValue>() {
			private int i = 0;
			
			@Override
			public boolean hasNext() {
				return i < size;
			}

			@Override
			public TweakerValue next() {
				return new TweakerByte(data[i++]);
			}

			@Override
			public void remove() {
				i--;
				removeByte(i);
			}
		};
	}
	
	@Override
	public TweakerArrayBytes asByteArray() {
		return this;
	}
	
	@Override
	public TweakerArrayInts asIntArray() {
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = this.data[i];
		}
		return new TweakerArrayInts(data);
	}
	
	@Override
	public TweakerValue index(String index) {
		if (index == null) {
			throw new TweakerExecuteException("Cannot index a byte array with a null value");
		} else if (index.equals("copy")) {
			return new TweakerArrayBytes(Arrays.copyOf(data, size));
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public NBTBase toTagValue(String name) {
		return new NBTTagByteArray(name, size < data.length ? Arrays.copyOf(data, size) : data);
	}
}
