package stanhebben.minetweaker.api.value;

import java.util.HashMap;

import stanhebben.minetweaker.api.TweakerExecuteException;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class TweakerTable extends TweakerValue {
	private HashMap<String, TweakerValue> contents;
	
	public TweakerTable() {
		contents = new HashMap<String, TweakerValue>();
	}

	@Override
	public NBTBase toTagValue(String name) throws TweakerExecuteException {
		return toTag(name);
	}
	
	@Override
	public NBTTagCompound toTag(String name) throws TweakerExecuteException {
		NBTTagCompound compound = new NBTTagCompound(name);
		for (String s : contents.keySet()) {
			compound.setTag(s, contents.get(s).toTagValue(s));
		}
		return compound;
	}

	@Override
	public TweakerValue index(TweakerValue index) throws TweakerExecuteException {
		if (index == null) throw new TweakerExecuteException("Cannot index a table with null");
		return contents.get(index.toBasicString());
	}

	@Override
	public TweakerValue index(String index) {
		return contents.get(index);
	}

	@Override
	public void indexSet(TweakerValue index, TweakerValue value) throws TweakerExecuteException {
		if (index == null) throw new TweakerExecuteException("Cannot index a table with null");
		contents.put(index.toBasicString(), value);
	}

	@Override
	public void indexSet(String index, TweakerValue value) {
		contents.put(index, value);
	}
	
	@Override
	public boolean contains(TweakerValue value) {
		return contents.containsKey(value.toBasicString());
	}

	@Override
	public String toString() {
		return "table";
	}
}
