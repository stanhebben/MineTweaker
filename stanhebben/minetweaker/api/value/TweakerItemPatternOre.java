package stanhebben.minetweaker.api.value;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.IPatternListener;

public class TweakerItemPatternOre extends TweakerItemPattern {
	private final String value;
	
	public TweakerItemPatternOre(String value) {
		this.value = value;
	}
	
	public String get() {
		return value;
	}
	
	@Override
	public boolean matches(TweakerItem item) {
		return false;
	}
	
	@Override
	public boolean matches(ItemStack item) {
		return false;
	}

	@Override
	public boolean matches(Object object) {
		if (object == null) return false;
		List<?> ores = OreDictionary.getOres(value);
		if (ores == object) return true;
		return value.equals(object);
	}

	@Override
	public boolean matches(int id) {
		return false;
	}

	@Override
	public boolean matches(int id, int meta) {
		return false;
	}
	
	@Override
	public String toPatternString() {
		return "oreDict." + value;
	}

	@Override
	public String toString() {
		return "ItemPattern:ore:" + value;
	}

	@Override
	public List<TweakerItem> getMatches() {
		return Collections.emptyList();
	}

	@Override
	public void addListener(IPatternListener listener) {
		// always empty
	}

	@Override
	public void removeListener(IPatternListener listener) {
		// always empty
	}
}
