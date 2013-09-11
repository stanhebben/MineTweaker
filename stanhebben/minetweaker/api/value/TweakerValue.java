package stanhebben.minetweaker.api.value;

import java.util.Iterator;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

/**
 * Core tweaker value class. All values used in MineTweaker scripts extend this class.
 * They can be converted to their respective types using either the as<type> 
 * methods, or the to<type> methods. The as - methods will return null if the
 * value could not be converted, whereas the to - methods will throw an exception.
 * 
 * TweakerValues contain a large number of methods, each of which can be
 * overridden to implement certain behavior. Overriding call() makes the value
 * callable and is used in method and function implementations. (@see TweakerFunction)
 * 
 * Overriding index() makes it possible to retrieve the contents of a value, as
 * used by many interfaces like recipes, minetweaker, oreDict, tile, item, ...
 * 
 * Overriding indexSet() makes it possible to set certain values. (such as used by
 * item.fuel = <value> or item.displayName = <value>).
 * 
 * Overriding add, sub, ... methods makes it possible to perform custom
 * arithmetic. Overriding equals and compare makes the == and comparison operands
 * functional.
 * 
 * @author Stan Hebben
 */
public abstract class TweakerValue {
	public static final double DELTA = 0.00001;
	
	private static final byte ID_BYTE = 1;
	private static final byte ID_SHORT = 2;
	private static final byte ID_INT = 3;
	private static final byte ID_LONG = 4;
	private static final byte ID_FLOAT = 5;
	private static final byte ID_DOUBLE = 6;
	private static final byte ID_BYTEARRAY = 7;
	private static final byte ID_STRING = 8;
	private static final byte ID_LIST = 9;
	private static final byte ID_COMPOUND = 10;
	private static final byte ID_INTARRAY = 11;
	
	// ----------------------
	// --- Static methods ---
	// ----------------------
	public static TweakerValue notNull(TweakerValue value, String error) {
		if (value == null || value == TweakerNull.INSTANCE) throw new TweakerExecuteException(error);
		return value;
	}
	
	public static boolean isNull(TweakerValue value) {
		return value == null || value == TweakerNull.INSTANCE;
	}
	
	public static TweakerValue fromNBT(NBTBase tag) {
		switch (tag.getId()) {
		case ID_BYTE:
			return new TweakerByte(((NBTTagByte) tag).data);
		case ID_SHORT:
			return new TweakerShort(((NBTTagShort) tag).data);
		case ID_INT:
			return new TweakerInt(((NBTTagInt) tag).data);
		case ID_LONG:
			return new TweakerLong(((NBTTagLong) tag).data);
		case ID_FLOAT:
			return new TweakerFloat(((NBTTagFloat) tag).data);
		case ID_DOUBLE:
			return new TweakerDouble(((NBTTagDouble) tag).data);
		case ID_BYTEARRAY:
			return new TweakerArrayBytes(((NBTTagByteArray) tag).byteArray);
		case ID_INTARRAY:
			return new TweakerArrayInts(((NBTTagIntArray) tag).intArray);
		case ID_STRING:
			return new TweakerString(((NBTTagString) tag).data);
		case ID_LIST:
			return new TweakerArrayNBT((NBTTagList) tag);
		case ID_COMPOUND:
			return new TweakerNBTCompound((NBTTagCompound) tag);
		default:
			throw new TweakerExecuteException("Unsupported tag type: " + tag.getId());
		}
	}
	
	// -------------------------
	// --- Generic Operators ---
	// -------------------------
	
	public NBTTagCompound toTag(String name) {
		throw new TweakerExecuteException("Cannot convert " + toString() + " to an NBT element");
	}
	
	public TweakerValue[] getTupleValues() {
		return new TweakerValue[] { this };
	}
	
	public TweakerBool asBool() {
		return null;
	}
	
	public TweakerBool toBool(String error) {
		TweakerBool result = asBool();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerByte asByte() {
		return null;
	}
	
	public TweakerByte toByte(String error) {
		TweakerByte result = asByte();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerShort asShort() {
		return null;
	}
	
	public TweakerShort toShort(String error) {
		TweakerShort result = asShort();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerInt asInt() {
		return null;
	}
	
	public TweakerInt toInt(String error) {
		TweakerInt result = asInt();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerLong asLong() {
		return null;
	}
	
	public TweakerLong toLong(String error) {
		TweakerLong result = asLong();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerString asString() {
		return null;
	}
	
	public TweakerFloat asFloat() {
		return null;
	}
	
	public TweakerFloat toFloat(String error) {
		TweakerFloat result = asFloat();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerDouble asDouble() {
		return null;
	}
	
	public TweakerDouble toDouble(String error) {
		TweakerDouble result = asDouble();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}

	public TweakerItem asItem() {
		return null;
	}
	
	public TweakerItem toItem(String error) {
		TweakerItem result = asItem();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerItemStack asItemStack() {
		return null;
	}
	
	public TweakerItemStack toItemStack(String error) {
		TweakerItemStack result = asItemStack();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerItemPattern asItemPattern() {
		return null;
	}
	
	public TweakerItemPattern toItemPattern(String error) {
		TweakerItemPattern result = asItemPattern();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public TweakerItemStackPattern asItemStackPattern() {
		return null;
	}
	
	public TweakerItemStackPattern toItemStackPattern(String error) {
		TweakerItemStackPattern result = asItemStackPattern();
		if (result == null) {
			throw new TweakerExecuteException(error);
		}
		return result;
	}
	
	public TweakerFluid asFluid() {
		return null;
	}
	
	public TweakerFluid toFluid(String error) {
		TweakerFluid result = asFluid();
		if (result == null) {
			throw new TweakerExecuteException(error);
		}
		return result;
	}
	
	public TweakerFluidStack asFluidStack() {
		return null;
	}
	
	public TweakerFluidStack toFluidStack(String error) {
		TweakerFluidStack result = asFluidStack();
		if (result == null) {
			throw new TweakerExecuteException(error);
		}
		return result;
	}
	
	public TweakerArray asArray() {
		return null;
	}

	public TweakerArray toArray(String error) {
		TweakerArray result = asArray();
		if (result == null) throw new TweakerExecuteException(error);
		return result;
	}
	
	public Object asRecipeItem() {
		return null;
	}
	
	public Object toRecipeItem(String error) {
		Object result = asRecipeItem();
		if (result == null) {
			throw new TweakerExecuteException(error);
		}
		return result;
	}
	
	public boolean toBasicBool() {
		TweakerBool result = asBool();
		if (result == null) throw new TweakerExecuteException(toString() + " cannot be converted to a bool");
		return result.get();
	}
	
	public int toBasicInt() {
		TweakerInt result = asInt();
		if (result == null) throw new TweakerExecuteException(toString() + " cannot be converted to an int");
		return result.get();
	}
	
	public long toBasicLong() {
		TweakerLong result = asLong();
		if (result == null) throw new TweakerExecuteException(toString() + " cannot be converted to a long");
		return result.get();
	}
	
	public String toBasicString() {
		TweakerString result = asString();
		if (result == null) throw new TweakerExecuteException(toString() + " cannot be converted to a string");
		return result.get();
	}
	
	// ------------------------
	// -- Optimization Types --
	// ------------------------
	
	public TweakerArrayBytes asByteArray() {
		return null;
	}
	
	public TweakerArrayInts asIntArray() {
		return null;
	}
	
	// ----------------
	// -- Operations --
	// ----------------
	
	public TweakerValue addAssign(TweakerValue other) {
		return add(other);
	}
	
	public TweakerValue subAssign(TweakerValue other) {
		return sub(other);
	}
	
	public TweakerValue mulAssign(TweakerValue other) {
		return mul(other);
	}
	
	public TweakerValue divAssign(TweakerValue other) {
		return div(other);
	}
	
	public TweakerValue modAssign(TweakerValue other) {
		return mod(other);
	}
	
	public TweakerValue orAssign(TweakerValue other) {
		return or(other);
	}
	
	public TweakerValue andAssign(TweakerValue other) {
		return and(other);
	}
	
	public TweakerValue xorAssign(TweakerValue other) {
		return xor(other);
	}
	
	public TweakerValue add(TweakerValue other) {
		throw new TweakerExecuteException("Cannot add " + toString() + " and " + (other == null ? "null" : other.toString()));
	}
	
	public TweakerValue sub(TweakerValue other) {
		throw new TweakerExecuteException("Cannot subtract " + (other == null ? "null" : other.toString()) + " from " + toString());
	}
	
	public TweakerValue mul(TweakerValue other) {
		throw new TweakerExecuteException("Cannot multiply " + toString() + " with " + (other == null ? "null" : other.toString()));
	}
	
	public TweakerValue div(TweakerValue other) {
		throw new TweakerExecuteException("Cannot divide " + toString() + " by " + (other == null ? "null" : other.toString()));
	}
	
	public TweakerValue mod(TweakerValue other) {
		throw new TweakerExecuteException("Cannot mod "  + toString() + " by " + (other == null ? "null" : other.toString()));
	}
	
	public TweakerValue neg() {
		throw new TweakerExecuteException("Cannot negate " + toString());
	}
	
	public TweakerValue not() {
		throw new TweakerExecuteException("Cannot invert " + toString());
	}
	
	public TweakerValue or(TweakerValue other) {
		throw new TweakerExecuteException("Cannot or " + toString() + " with " + (other == null ? "null" : other.toString()));
	}

	public TweakerValue and(TweakerValue other) {
		throw new TweakerExecuteException("Cannot and " + toString() + " with " + (other == null ? "null" : other.toString()));
	}
	
	public TweakerValue xor(TweakerValue other) {
		throw new TweakerExecuteException("Cannot xor " + toString() + " with " + (other == null ? "null" : other.toString()));
	}
	
	public boolean equals(TweakerValue other) {
		return this == other;
	}
	
	public int compare(TweakerValue other) {
		throw new TweakerExecuteException(toString() + " does not support comparison");
	}
	
	public TweakerValue index(TweakerValue index) {
		return index(notNull(index, "index must not be null").toBasicString());
	}
	
	public TweakerValue index(int index) {
		throw new TweakerExecuteException(toString() + " does not support indexing with integers");
	}
	
	public TweakerValue index(String index) {
		throw new TweakerExecuteException(toString() + " does not support indexing with strings");
	}
	
	public void indexSet(TweakerValue index, TweakerValue value) {
		indexSet(notNull(index, "cannot index by null").toBasicString(), value);
	}
	
	public void indexSet(String index, TweakerValue value) {
		throw new TweakerExecuteException(toString() + " does not support assignments indexed with strings");
	}
	
	public boolean contains(TweakerValue value) {
		throw new TweakerExecuteException("cannot use the in operand on " + toString());
	}
	
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		throw new TweakerExecuteException(toString() + " cannot be called");
	}
	
	public NBTBase toTagValue(String name) {
		return null;
	}

	public Iterator<TweakerValue> iterator() {
		throw new TweakerExecuteException(toString() + " is not iterable");
	}
	
	// --------------------------
	// --- Specific operators ---
	// --------------------------
	
	public TweakerValue addToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue addToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue addToFloat(float value) {
		throw new TweakerExecuteException("Cannot add a float value to " + toString());
	}
	
	public TweakerValue addToDouble(double value) {
		throw new TweakerExecuteException("Cannot add a double value to " + toString());
	}
	
	public TweakerValue subToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue subToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue subToFloat(float value) {
		throw new TweakerExecuteException("Cannot add a float value to " + toString());
	}
	
	public TweakerValue subToDouble(double value) {
		throw new TweakerExecuteException("Cannot add a double value to " + toString());
	}

	public TweakerValue mulToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue mulToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue mulToFloat(float value) {
		throw new TweakerExecuteException("Cannot add a float value to " + toString());
	}
	
	public TweakerValue mulToDouble(double value) {
		throw new TweakerExecuteException("Cannot add a double value to " + toString());
	}

	public TweakerValue divToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue divToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue divToFloat(float value) {
		throw new TweakerExecuteException("Cannot add a float value to " + toString());
	}
	
	public TweakerValue divToDouble(double value) {
		throw new TweakerExecuteException("Cannot add a double value to " + toString());
	}

	public TweakerValue modToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue modToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue modToFloat(float value) {
		throw new TweakerExecuteException("Cannot add a float value to " + toString());
	}
	
	public TweakerValue modToDouble(double value) {
		throw new TweakerExecuteException("Cannot add a double value to " + toString());
	}

	public TweakerValue orToByte(byte value) {
		throw new TweakerExecuteException("Cannot add a byte value to " + toString());
	}
	
	public TweakerValue orToShort(short value) {
		throw new TweakerExecuteException("Cannot add a short value to " + toString());
	}
	
	public TweakerValue orToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue orToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue andToByte(byte value) {
		throw new TweakerExecuteException("Cannot add a byte value to " + toString());
	}
	
	public TweakerValue andToShort(short value) {
		throw new TweakerExecuteException("Cannot add a short value to " + toString());
	}
	
	public TweakerValue andToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue andToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public TweakerValue xorToByte(byte value) {
		throw new TweakerExecuteException("Cannot add a byte value to " + toString());
	}
	
	public TweakerValue xorToShort(short value) {
		throw new TweakerExecuteException("Cannot add a short value to " + toString());
	}
	
	public TweakerValue xorToInt(int value) {
		throw new TweakerExecuteException("Cannot add an int value to " + toString());
	}
	
	public TweakerValue xorToLong(long value) {
		throw new TweakerExecuteException("Cannot add a long value to " + toString());
	}
	
	public boolean equalsInt(int value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with long value");
	}
	
	public boolean equalsLong(long value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with long value");
	}
	
	public boolean equalsFloat(float value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with float value");
	}
	
	public boolean equalsDouble(double value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with double value");
	}
	
	public int compareInt(int value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with int value");
	}
	
	public int compareLong(long value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with long value");
	}
	
	public int compareFloat(float value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with float value");
	}
	
	public int compareDouble(double value) {
		throw new TweakerExecuteException("Cannot compare " + toString() + " with double value");
	}
	
	@Override
	public abstract String toString();
}
