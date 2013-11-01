package stanhebben.minetweaker.api.value;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagLong;
import stanhebben.minetweaker.api.TweakerExecuteException;

public final class TweakerLong extends TweakerValue {
	private final long value;
	
	public TweakerLong(long value) {
		this.value = value;
	}
	
	public long get() {
		return value;
	}
	
	@Override
	public long toBasicLong() {
		return value;
	}
	
	@Override
	public NBTBase toTagValue(String name) {
		return new NBTTagLong(name, value);
	}

	@Override
	public TweakerByte asByte() {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
			return null;
		} else {
			return new TweakerByte((byte) value);
		}
	}

	@Override
	public TweakerByte toByte(String error) {
		if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
			throw new TweakerExecuteException("Value outside byte range, cannot convert: " + value);
		} else {
			return new TweakerByte((byte) value);
		}
	}

	@Override
	public TweakerShort asShort() {
		if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
			return null;
		} else {
			return new TweakerShort((short) value);
		}
	}

	@Override
	public TweakerShort toShort(String error) {
		if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
			throw new TweakerExecuteException("Value outside short range, cannot convert: " + value);
		} else {
			return new TweakerShort((short) value);
		}
	}

	@Override
	public TweakerInt asInt() {
		if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
			return null;
		} else {
			return new TweakerInt((int) value);
		}
	}
	
	@Override
	public TweakerInt toInt(String error) {
		if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
			throw new TweakerExecuteException("Value outside int range, cannot convert: " + value);
		} else {
			return new TweakerInt((int) value);
		}
	}
	
	@Override
	public TweakerLong asLong() {
		return this;
	}
	
	@Override
	public TweakerLong toLong(String error) {
		return this;
	}

	@Override
	public TweakerFloat asFloat() {
		return new TweakerFloat(value);
	}

	@Override
	public TweakerDouble asDouble() {
		return new TweakerDouble(value);
	}
	
	@Override
	public TweakerString asString() {
		return new TweakerString(Long.toString(value));
	}

	@Override
	public TweakerValue add(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot add null to a long value");
		return other.addToLong(value);
	}
	
	@Override
	public TweakerValue sub(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot subtract null from a long value");
		return other.subToLong(value);
	}
	
	@Override
	public TweakerValue mul(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot multiply a long value by null");
		return other.mulToLong(value);
	}
	
	@Override
	public TweakerValue div(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot divide a long value by null");
		return other.divToLong(value);
	}
	
	@Override
	public TweakerValue mod(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot modulo a long value by null");
		return other.modToLong(value);
	}
	
	@Override
	public TweakerValue neg() {
		return new TweakerLong(-value);
	}
	
	@Override
	public TweakerValue not() {
		return new TweakerLong(~value);
	}
	
	@Override
	public TweakerValue or(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot or a long value with null");
		return other.orToLong(value);
	}
	
	@Override
	public TweakerValue and(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot and a long value with null");
		return other.andToLong(value);
	}
	
	@Override
	public TweakerValue xor(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot xor a long value with null");
		return other.xorToLong(value);
	}
	
	@Override
	public boolean equals(TweakerValue other) {
		if (other == null) return false;
		return other.equalsLong(value);
	}
	
	@Override
	public int compare(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot compare a long value with null");
		return other.compareLong(value);
	}

	@Override
	public TweakerValue index(String index) {
		if (index.equals("abs")) {
			return new TweakerLong(Math.abs(value));
		} else {
			throw new TweakerExecuteException("Unknown member " + index + " of byte value");
		}
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}
	
	// --------------------------
	// --- Specific operators ---
	// --------------------------
	
	@Override
	public TweakerValue addToInt(int value) {
		return new TweakerLong(this.value + value);
	}
	
	@Override
	public TweakerValue addToLong(long value) {
		return new TweakerLong(this.value + value);
	}
	
	@Override
	public TweakerValue addToFloat(float value) {
		return new TweakerFloat(value + this.value);
	}
	
	@Override
	public TweakerValue addToDouble(double value) {
		return new TweakerDouble(value + this.value);
	}
	
	@Override
	public TweakerValue subToInt(int value) {
		return new TweakerLong(value - this.value);
	}
	
	@Override
	public TweakerValue subToLong(long value) {
		return new TweakerLong(value - this.value);
	}
	
	@Override
	public TweakerValue subToFloat(float value) {
		return new TweakerFloat(value - this.value);
	}
	
	@Override
	public TweakerValue subToDouble(double value) {
		return new TweakerDouble(value - this.value);
	}
	
	@Override
	public TweakerValue mulToInt(int value) {
		return new TweakerLong(value * this.value);
	}
	
	@Override
	public TweakerValue mulToLong(long value) {
		return new TweakerLong(value * this.value);
	}
	
	@Override
	public TweakerValue mulToFloat(float value) {
		return new TweakerFloat(value * this.value);
	}
	
	@Override
	public TweakerValue mulToDouble(double value) {
		return new TweakerDouble(value * this.value);
	}
	
	@Override
	public TweakerValue divToInt(int value) {
		if (this.value == 0) throw new TweakerExecuteException("Cannot divide an int value by zero");
		return new TweakerLong(value / this.value);
	}
	
	@Override
	public TweakerValue divToLong(long value) {
		if (this.value == 0) throw new TweakerExecuteException("Cannot divide a long value by zero");
		return new TweakerLong(value / this.value);
	}
	
	@Override
	public TweakerValue divToFloat(float value) {
		return new TweakerFloat(value / this.value);
	}
	
	@Override
	public TweakerValue divToDouble(double value) {
		return new TweakerDouble(value / this.value);
	}

	@Override
	public TweakerValue modToInt(int value) {
		if (this.value == 0) throw new TweakerExecuteException("Cannot modulo an int value by zero");
		return new TweakerLong(value % this.value);
	}
	
	@Override
	public TweakerValue modToLong(long value) {
		if (this.value == 0) throw new TweakerExecuteException("Cannot modulo a long value by zero");
		return new TweakerLong(value % this.value);
	}
	
	@Override
	public TweakerValue modToFloat(float value) {
		return new TweakerFloat(value % this.value);
	}
	
	@Override
	public TweakerValue modToDouble(double value) {
		return new TweakerDouble(value % this.value);
	}

	@Override
	public TweakerValue orToByte(byte value) {
		return new TweakerLong(value | this.value);
	}
	
	@Override
	public TweakerValue orToShort(short value) {
		return new TweakerLong(value | this.value);
	}
	
	@Override
	public TweakerValue orToInt(int value) {
		return new TweakerLong(value | this.value);
	}
	
	@Override
	public TweakerValue orToLong(long value) {
		return new TweakerLong(value | this.value);
	}
	
	@Override
	public TweakerValue andToByte(byte value) {
		return new TweakerByte((byte) (value & this.value));
	}
	
	@Override
	public TweakerValue andToShort(short value) {
		return new TweakerShort((short) (value & this.value));
	}
	
	@Override
	public TweakerValue andToInt(int value) {
		return new TweakerInt((int) (value & this.value));
	}
	
	@Override
	public TweakerValue andToLong(long value) {
		return new TweakerLong(value & this.value);
	}
	
	@Override
	public TweakerValue xorToByte(byte value) {
		return new TweakerLong(value ^ this.value);
	}
	
	@Override
	public TweakerValue xorToShort(short value) {
		return new TweakerLong(value ^ this.value);
	}
	
	@Override
	public TweakerValue xorToInt(int value) {
		return new TweakerLong(value ^ this.value);
	}
	
	@Override
	public TweakerValue xorToLong(long value) {
		return new TweakerLong(value ^ this.value);
	}
	
	@Override
	public boolean equalsInt(int value) {
		return this.value == value;
	}
	
	@Override
	public boolean equalsLong(long value) {
		return this.value == value;
	}
	
	@Override
	public boolean equalsFloat(float value) {
		return Math.abs(value - this.value) < DELTA;
	}
	
	@Override
	public boolean equalsDouble(double value) {
		return Math.abs(value - this.value) < DELTA;
	}
	
	@Override
	public int compareInt(int value) {
		return Long.compare(value, this.value);
	}
	
	@Override
	public int compareLong(long value) {
		return Long.compare(value, this.value);
	}
	
	@Override
	public int compareFloat(float value) {
		if (Math.abs(value - this.value) < DELTA) {
			return 0;
		} else if (value > this.value) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public int compareDouble(double value) {
		if (Math.abs(value - this.value) < DELTA) {
			return 0;
		} else if (value > this.value) {
			return 1;
		} else {
			return 0;
		}
	}
}
