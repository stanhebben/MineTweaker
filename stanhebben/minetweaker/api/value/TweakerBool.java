package stanhebben.minetweaker.api.value;

import net.minecraft.nbt.NBTBase;

public final class TweakerBool extends TweakerValue {
	public static final TweakerBool TRUE = new TweakerBool(true);
	public static final TweakerBool FALSE = new TweakerBool(false);
	
	public static TweakerBool get(boolean value) {
		return value ? TRUE : FALSE;
	}
	
	private final boolean value;
	
	private TweakerBool(boolean value) {
		this.value = value;
	}
	
	public boolean get() {
		return value;
	}
	
	@Override
	public TweakerBool asBool() {
		return this;
	}
	
	@Override
	public boolean toBasicBool() {
		return value;
	}

	@Override
	public NBTBase toTagValue(String name) {
		return null;
	}

	@Override
	public String toString() {
		return value ? "true" : "false";
	}
}
