package stanhebben.minetweaker.api.value;

import stanhebben.minetweaker.api.TweakerExecuteException;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;

public class TweakerDouble extends TweakerValue {
	private double value;
	
	public TweakerDouble(double value) {
		this.value = value;
	}
	
	public double get() {
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
	public TweakerFloat asFloat() {
		return new TweakerFloat((float) value);
	}
	
	@Override
	public TweakerDouble asDouble() {
		return this;
	}
	
	@Override
	public TweakerString asString() {
		return new TweakerString(Double.toString(value));
	}

	@Override
	public NBTBase toTagValue(String name) {
		return new NBTTagDouble(name, value);
	}
	
	@Override
	public TweakerValue add(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot add null to a double");
		return other.addToDouble(value);
	}

	@Override
	public TweakerValue sub(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot subtract null from a double");
		return other.subToDouble(value);
	}

	@Override
	public TweakerValue mul(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot multiply a double with null");
		return other.mulToDouble(value);
	}

	@Override
	public TweakerValue div(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot divide a double by null");
		return other.divToDouble(value);
	}

	@Override
	public TweakerValue mod(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot modulo a double with null");
		return other.modToDouble(value);
	}

	@Override
	public TweakerValue neg() {
		return new TweakerDouble(-value);
	}

	@Override
	public boolean equals(TweakerValue other) {
		if (other == null) return false;
		return other.equalsDouble(value);
	}

	@Override
	public int compare(TweakerValue other) {
		if (other == null) throw new TweakerExecuteException("Cannot compare a double with null");
		return other.compareDouble(value);
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ABS:
				return new TweakerDouble(Math.abs(value));
			case ISNAN:
				return TweakerBool.get(Double.isNaN(value));
			case ISINFINITE:
				return TweakerBool.get(Double.isInfinite(value));
			default:
				throw new TweakerExecuteException("Unknown member " + index + " of double value");
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
		return new TweakerDouble(this.value + value);
	}
	
	@Override
	public TweakerValue addToLong(long value) {
		return new TweakerDouble(this.value + value);
	}
	
	@Override
	public TweakerValue addToFloat(float value) {
		return new TweakerDouble(value + this.value);
	}
	
	@Override
	public TweakerValue addToDouble(double value) {
		return new TweakerDouble(value + this.value);
	}
	
	@Override
	public TweakerValue subToInt(int value) {
		return new TweakerDouble(value - this.value);
	}
	
	@Override
	public TweakerValue subToLong(long value) {
		return new TweakerDouble(value - this.value);
	}
	
	@Override
	public TweakerValue subToFloat(float value) {
		return new TweakerDouble(value - this.value);
	}
	
	@Override
	public TweakerValue subToDouble(double value) {
		return new TweakerDouble(value - this.value);
	}
	
	@Override
	public TweakerValue mulToInt(int value) {
		return new TweakerDouble(value * this.value);
	}
	
	@Override
	public TweakerValue mulToLong(long value) {
		return new TweakerDouble(value * this.value);
	}
	
	@Override
	public TweakerValue mulToFloat(float value) {
		return new TweakerDouble(value * this.value);
	}
	
	@Override
	public TweakerValue mulToDouble(double value) {
		return new TweakerDouble(value * this.value);
	}
	
	@Override
	public TweakerValue divToInt(int value) {
		return new TweakerDouble(value / this.value);
	}
	
	@Override
	public TweakerValue divToLong(long value) {
		return new TweakerDouble(value / this.value);
	}
	
	@Override
	public TweakerValue divToFloat(float value) {
		return new TweakerDouble(value / this.value);
	}
	
	@Override
	public TweakerValue divToDouble(double value) {
		return new TweakerDouble(value / this.value);
	}

	@Override
	public TweakerValue modToInt(int value) {
		return new TweakerDouble(value % this.value);
	}
	
	@Override
	public TweakerValue modToLong(long value) {
		return new TweakerDouble(value % this.value);
	}

	@Override
	public TweakerValue modToFloat(float value) {
		return new TweakerDouble(value % this.value);
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
	public boolean equalsFloat(float value) {
		return Math.abs(this.value - value) < DELTA;
	}

	@Override
	public boolean equalsDouble(double value) {
		return Math.abs(this.value - value) < DELTA;
	}

	@Override
	public int compareInt(int value) {
		if (Math.abs(value - this.value) < DELTA) {
			return 0;
		} else if (value > this.value) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int compareLong(long value) {
		if (Math.abs(value - this.value) < DELTA) {
			return 0;
		} else if (value > this.value) {
			return 1;
		} else {
			return 0;
		}
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
