package stanhebben.minetweaker.api.value;

import stanhebben.minetweaker.api.TweakerExecuteException;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;

public class TweakerFloat extends TweakerValue {
	private float value;

	public TweakerFloat(float value) {
		this.value = value;
	}
	
	public float get() {
		return value;
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
			throw new TweakerExecuteException("Value out of byte range, cannot convert: " + value);
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
			throw new TweakerExecuteException("Value out of short range, cannot convert: " + value);
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
	public TweakerInt toInt(String message) {
		if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
			throw new TweakerExecuteException("Value out of integer range, cannot convert: " + value);
		} else {
			return new TweakerInt((int) value);
		}
	}
	
	@Override
	public TweakerLong asLong() {
		if (value < Long.MIN_VALUE || value > Long.MAX_VALUE) {
			return null;
		} else {
			return new TweakerLong((long) value);
		}
	}
	
	@Override
	public TweakerLong toLong(String error) {
		if (value < Long.MIN_VALUE || value > Long.MAX_VALUE) {
			throw new TweakerExecuteException("Value out of long range, cannot convert: " + value);
		} else {
			return new TweakerLong((long) value);
		}
	}
	
	@Override
	public TweakerFloat asFloat() throws TweakerExecuteException {
		return this;
	}
	
	@Override
	public TweakerDouble asDouble() throws TweakerExecuteException {
		return new TweakerDouble(value);
	}
	
	@Override
	public TweakerString asString() {
		return new TweakerString(Float.toString(value));
	}

	@Override
	public NBTBase toTagValue(String name) {
		return new NBTTagFloat(name, value);
	}
	
	@Override
	public TweakerValue add(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot add null to a float value");
		return other.addToFloat(value);
	}

	@Override
	public TweakerValue sub(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot subtract null from a float value");
		return other.subToFloat(value);
	}

	@Override
	public TweakerValue mul(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot multiply a float value by null");
		return other.mulToFloat(value);
	}

	@Override
	public TweakerValue div(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot divide a float value by null");
		return other.divToFloat(value);
	}

	@Override
	public TweakerValue mod(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot modulo a float value with null");
		return other.modToFloat(value);
	}

	@Override
	public TweakerValue neg() {
		return new TweakerFloat(-value);
	}

	@Override
	public boolean equals(TweakerValue other) {
		if (other == null) return false;
		return other.equalsFloat(value);
	}
	
	@Override
	public int compare(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot compare a float value with null");
		return other.compareFloat(value);
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ABS:
				return new TweakerFloat(Math.abs(value));
			case ISNAN:
				return TweakerBool.get(Float.isNaN(value));
			case ISINFINITE:
				return TweakerBool.get(Float.isInfinite(value));
			default:
				throw new TweakerExecuteException("Unknown member " + index + " of float value");
		}
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}
	
	// --------------------------
	// --- Specific operators ---
	// --------------------------
	
	@Override
	public TweakerValue addToInt(int value) {
		return new TweakerFloat(this.value + value);
	}
	
	@Override
	public TweakerValue addToLong(long value) {
		return new TweakerFloat(this.value + value);
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
		return new TweakerFloat(value - this.value);
	}
	
	@Override
	public TweakerValue subToLong(long value) {
		return new TweakerFloat(value - this.value);
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
		return new TweakerFloat(value * this.value);
	}
	
	@Override
	public TweakerValue mulToLong(long value) {
		return new TweakerFloat(value * this.value);
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
		return new TweakerFloat(value / this.value);
	}
	
	@Override
	public TweakerValue divToLong(long value) {
		return new TweakerFloat(value / this.value);
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
		return new TweakerFloat(value % this.value);
	}
	
	@Override
	public TweakerValue modToLong(long value) {
		return new TweakerFloat(value % this.value);
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
	public boolean equalsInt(int value) {
		return Math.abs(this.value - value) < DELTA;
	}

	@Override
	public boolean equalsLong(long value) {
		return Math.abs(this.value - value) < DELTA;
	}

	@Override
	public boolean equalsDouble(double value) {
		return Math.abs(this.value - value) < DELTA;
	}

	@Override
	public int compareInt(int value) {
		if (Math.abs(this.value - value) < DELTA) {
			return 0;
		} else if (this.value > value) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int compareLong(long value) {
		if (Math.abs(this.value - value) < DELTA) {
			return 0;
		} else if (this.value > value) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public int compareFloat(float value) {
		if (Math.abs(this.value - value) < DELTA) {
			return 0;
		} else if (this.value > value) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public int compareDouble(double value) {
		if (Math.abs(this.value - value) < DELTA) {
			return 0;
		} else if (this.value > value) {
			return 1;
		} else {
			return 0;
		}
	}
}
