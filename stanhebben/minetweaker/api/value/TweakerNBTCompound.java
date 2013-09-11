package stanhebben.minetweaker.api.value;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class TweakerNBTCompound extends TweakerValue {
	private final NBTTagCompound value;
	
	public TweakerNBTCompound(NBTTagCompound value) {
		this.value = value;
	}
	
	@Override
	public TweakerValue index(String name) {
		NBTBase tag = value.getTag(name);
		if (tag == null) {
			if (name.equals("copy") || name.equals("__copy")) {
				return new TweakerNBTCompound((NBTTagCompound) value.copy());
			} else {
				return null;
			}
		} else {
			return TweakerValue.fromNBT(tag);
		}
	}
	
	@Override
	public void indexSet(String name, TweakerValue value) {
		this.value.setTag(name, value.toTagValue(name));
	}
	
	@Override
	public boolean contains(TweakerValue value) {
		return this.value.hasKey(value.toBasicString());
	}

	@Override
	public NBTBase toTagValue(String name) {
		if ((value.getName() == null && name == null) || value.getName().equals(name)) {
			return value;
		} else {
			return value.copy().setName(name);
		}
	}

	@Override
	public String toString() {
		return "<NBTCompound>";
	}
}
