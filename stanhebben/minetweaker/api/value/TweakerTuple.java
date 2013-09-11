package stanhebben.minetweaker.api.value;

import java.util.Iterator;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.minetweaker.api.TweakerNameSpace;

public class TweakerTuple extends TweakerValue {
	private TweakerValue[] values;
	
	public TweakerTuple(TweakerValue[] values) {
		this.values = values;
	}
	
	// -------------------------
	// --- Generic Operators ---
	// -------------------------
	
	@Override
	public NBTTagCompound toTag(String name) {
		return values[0].toTag(name);
	}
	
	@Override
	public TweakerValue[] getTupleValues() {
		return values;
	}

	@Override
	public TweakerBool asBool() {
		return values[0].asBool();
	}
	
	@Override
	public TweakerByte asByte() {
		return values[0].asByte();
	}

	@Override
	public TweakerShort asShort() {
		return values[0].asShort();
	}

	@Override
	public TweakerInt asInt() {
		return values[0].asInt();
	}

	@Override
	public TweakerLong asLong() {
		return values[0].asLong();
	}

	@Override
	public TweakerString asString() {
		return values[0].asString();
	}
	
	@Override
	public TweakerFloat asFloat() {
		return values[0].asFloat();
	}
	
	@Override
	public TweakerDouble asDouble() {
		return values[0].asDouble();
	}

	@Override
	public TweakerItem asItem() {
		return values[0].asItem();
	}
	
	@Override
	public TweakerItemStack asItemStack() {
		return values[0].asItemStack();
	}
	
	@Override
	public TweakerItemPattern asItemPattern() {
		return values[0].asItemPattern();
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return values[0].asItemStackPattern();
	}
	
	@Override
	public TweakerArray asArray() {
		return values[0].asArray();
	}

	@Override
	public Object asRecipeItem() {
		return values[0].asRecipeItem();
	}
	
	@Override
	public boolean toBasicBool() {
		return values[0].toBasicBool();
	}

	@Override
	public int toBasicInt() {
		return values[0].toBasicInt();
	}
	
	@Override
	public long toBasicLong() {
		return values[0].toBasicLong();
	}

	@Override
	public String toBasicString() {
		return values[0].toBasicString();
	}

	@Override
	public TweakerArrayBytes asByteArray() {
		return values[0].asByteArray();
	}

	@Override
	public TweakerArrayInts asIntArray() {
		return values[0].asIntArray();
	}

	@Override
	public TweakerValue addAssign(TweakerValue other) {
		return values[0].addAssign(other);
	}

	@Override
	public TweakerValue subAssign(TweakerValue other) {
		return values[0].subAssign(other);
	}

	@Override
	public TweakerValue mulAssign(TweakerValue other) {
		return values[0].mulAssign(other);
	}

	@Override
	public TweakerValue divAssign(TweakerValue other) {
		return values[0].divAssign(other);
	}

	@Override
	public TweakerValue modAssign(TweakerValue other) {
		return values[0].modAssign(other);
	}

	@Override
	public TweakerValue orAssign(TweakerValue other) {
		return values[0].orAssign(other);
	}

	@Override
	public TweakerValue andAssign(TweakerValue other) {
		return values[0].andAssign(other);
	}

	@Override
	public TweakerValue xorAssign(TweakerValue other) {
		return values[0].xorAssign(other);
	}

	@Override
	public TweakerValue add(TweakerValue other) {
		return values[0].add(other);
	}

	@Override
	public TweakerValue sub(TweakerValue other) {
		return values[0].sub(other);
	}

	@Override
	public TweakerValue mul(TweakerValue other) {
		return values[0].mul(other);
	}

	@Override
	public TweakerValue div(TweakerValue other) {
		return values[0].div(other);
	}

	@Override
	public TweakerValue mod(TweakerValue other) {
		return values[0].mod(other);
	}

	@Override
	public TweakerValue neg() {
		return values[0].neg();
	}

	@Override
	public TweakerValue not() {
		return values[0].not();
	}

	@Override
	public TweakerValue or(TweakerValue other) {
		return values[0].or(other);
	}

	@Override
	public TweakerValue and(TweakerValue other) {
		return values[0].and(other);
	}

	@Override
	public TweakerValue xor(TweakerValue other) {
		return values[0].xor(other);
	}

	@Override
	public boolean equals(TweakerValue other) {
		return values[0].equals(other);
	}

	@Override
	public int compare(TweakerValue other) {
		return values[0].compare(other);
	}

	@Override
	public TweakerValue index(TweakerValue index) {
		return values[0].index(index);
	}

	@Override
	public TweakerValue index(int index) {
		return values[0].index(index);
	}

	@Override
	public TweakerValue index(String index) {
		return values[0].index(index);
	}

	@Override
	public void indexSet(TweakerValue index, TweakerValue value) {
		values[0].indexSet(index, value);
	}

	@Override
	public void indexSet(String index, TweakerValue value) {
		values[0].indexSet(index, value);
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		return values[0].call(namespace, arguments);
	}

	@Override
	public NBTBase toTagValue(String name) {
		return values[0].toTagValue(name);
	}

	@Override
	public Iterator<TweakerValue> iterator() {
		return values[0].iterator();
	}
	
	// --------------------------
	// --- Specific operators ---
	// --------------------------

	@Override
	public TweakerValue addToInt(int value) {
		return values[0].addToInt(value);
	}

	@Override
	public TweakerValue addToLong(long value) {
		return values[0].addToLong(value);
	}

	@Override
	public TweakerValue addToFloat(float value) {
		return values[0].addToFloat(value);
	}

	@Override
	public TweakerValue addToDouble(double value) {
		return values[0].addToDouble(value);
	}

	@Override
	public TweakerValue subToInt(int value) {
		return values[0].subToInt(value);
	}

	@Override
	public TweakerValue subToLong(long value) {
		return values[0].subToLong(value);
	}

	@Override
	public TweakerValue subToFloat(float value) {
		return values[0].subToFloat(value);
	}

	@Override
	public TweakerValue subToDouble(double value) {
		return values[0].subToDouble(value);
	}

	@Override
	public TweakerValue mulToInt(int value) {
		return values[0].mulToInt(value);
	}

	@Override
	public TweakerValue mulToLong(long value) {
		return values[0].mulToLong(value);
	}

	@Override
	public TweakerValue mulToFloat(float value) {
		return values[0].mulToFloat(value);
	}

	@Override
	public TweakerValue mulToDouble(double value) {
		return values[0].mulToDouble(value);
	}

	@Override
	public TweakerValue divToInt(int value) {
		return values[0].divToInt(value);
	}

	@Override
	public TweakerValue divToLong(long value) {
		return values[0].divToLong(value);
	}

	@Override
	public TweakerValue divToFloat(float value) {
		return values[0].divToFloat(value);
	}

	@Override
	public TweakerValue divToDouble(double value) {
		return values[0].divToDouble(value);
	}

	@Override
	public TweakerValue modToInt(int value) {
		return values[0].modToInt(value);
	}

	@Override
	public TweakerValue modToLong(long value) {
		return values[0].modToLong(value);
	}

	@Override
	public TweakerValue modToFloat(float value) {
		return values[0].modToFloat(value);
	}

	@Override
	public TweakerValue modToDouble(double value) {
		return values[0].modToDouble(value);
	}

	@Override
	public TweakerValue orToByte(byte value) {
		return values[0].orToByte(value);
	}

	@Override
	public TweakerValue orToShort(short value) {
		return values[0].orToShort(value);
	}

	@Override
	public TweakerValue orToInt(int value) {
		return values[0].orToInt(value);
	}

	@Override
	public TweakerValue orToLong(long value) {
		return values[0].orToLong(value);
	}

	@Override
	public TweakerValue andToByte(byte value) {
		return values[0].andToByte(value);
	}

	@Override
	public TweakerValue andToShort(short value) {
		return values[0].andToShort(value);
	}

	@Override
	public TweakerValue andToInt(int value) {
		return values[0].andToInt(value);
	}

	@Override
	public TweakerValue andToLong(long value) {
		return values[0].andToLong(value);
	}

	@Override
	public TweakerValue xorToByte(byte value) {
		return values[0].xorToByte(value);
	}

	@Override
	public TweakerValue xorToShort(short value) {
		return values[0].xorToShort(value);
	}

	@Override
	public TweakerValue xorToInt(int value) {
		return values[0].xorToInt(value);
	}

	@Override
	public TweakerValue xorToLong(long value) {
		return values[0].xorToLong(value);
	}

	@Override
	public boolean equalsInt(int value) {
		return values[0].equalsInt(value);
	}

	@Override
	public boolean equalsLong(long value) {
		return values[0].equalsLong(value);
	}

	@Override
	public boolean equalsFloat(float value) {
		return values[0].equalsFloat(value);
	}

	@Override
	public boolean equalsDouble(double value) {
		return values[0].equalsDouble(value);
	}

	@Override
	public int compareInt(int value) {
		return values[0].compareInt(value);
	}

	@Override
	public int compareLong(long value) {
		return values[0].compareLong(value);
	}

	@Override
	public int compareFloat(float value) {
		return values[0].compareFloat(value);
	}

	@Override
	public int compareDouble(double value) {
		return values[0].compareDouble(value);
	}
	
	@Override
	public String toString() {
		return values[0].toString();
	}
}
