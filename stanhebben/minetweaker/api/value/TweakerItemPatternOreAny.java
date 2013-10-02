package stanhebben.minetweaker.api.value;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.oredict.OreEntryListener;
import stanhebben.minetweaker.oredict.TweakerOreDict;
import stanhebben.minetweaker.oredict.TweakerOreEntry;

public class TweakerItemPatternOreAny extends TweakerItemPattern {
	private final String value;
	
	public TweakerItemPatternOreAny(String value) {
		this.value = value;
	}
	
	@Override
	public boolean matches(TweakerItem item) {
		if (item == null) return false;
		
		ArrayList<ItemStack> ores = OreDictionary.getOres(value);
		for (ItemStack oreStack : ores) {
			if (oreStack.itemID == item.getItemId()
					&& (!oreStack.getHasSubtypes() || oreStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || oreStack.getItemDamage() == item.getItemSubId())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean matches(ItemStack item) {
		if (item == null) return false;
		
		ArrayList<ItemStack> ores = OreDictionary.getOres(value);
		for (ItemStack oreStack : ores) {
			if (oreStack.itemID == item.itemID
					&& (!oreStack.getHasSubtypes() || oreStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || oreStack.getItemDamage() == item.getItemDamage())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean matches(Object object) {
		if (object == null) return false;
		if (object == OreDictionary.getOres(value)) return true;
		if (value.equals(object)) return true;
		if (!(object instanceof ItemStack)) return false;
		return matches((ItemStack) object);
	}

	@Override
	public boolean matches(int id) {
		ArrayList<ItemStack> ores = OreDictionary.getOres(value);
		for (ItemStack oreStack : ores) {
			if (!oreStack.getHasSubtypes() && oreStack.itemID == id) return true;
		}
		return false;
	}

	@Override
	public boolean matches(int id, int meta) {
		ArrayList<ItemStack> ores = OreDictionary.getOres(value);
		for (ItemStack oreStack : ores) {
			if (oreStack.itemID == id && oreStack.getItemDamage() == meta) return true;
		}
		return false;
	}
	
	@Override
	public String toPatternString() {
		return "oreDict." + value + ".any";
	}

	@Override
	public String toString() {
		return "Pattern:oreAny:" + value;
	}

	@Override
	public List<TweakerItem> getMatches() {
		return TweakerOreDict.getEntry(value).getItems();
	}

	@Override
	public void addListener(IPatternListener listener) {
		TweakerOreDict.getEntry(value).addListener(new OrePatternListener(listener));
	}

	@Override
	public void removeListener(IPatternListener listener) {
		TweakerOreDict.getEntry(value).removeListener(new OrePatternListener(listener));
	}
	
	private static class OrePatternListener implements OreEntryListener {
		private final IPatternListener base;
		
		OrePatternListener(IPatternListener base) {
			this.base = base;
		}

		public void itemAdded(TweakerOreEntry entry, TweakerItem item) {
			base.onPatternResultAdded(item);
		}

		public void itemRemoved(TweakerOreEntry entry, TweakerItem item) {
			base.onPatternResultRemoved(item);
		}

		public void itemWildcardAdded(TweakerOreEntry entry, int id) {
			List<ItemStack> subItems = new ArrayList<ItemStack>();
			MineTweakerUtil.getSubItems(id, subItems);
			for (ItemStack stack : subItems) {
				base.onPatternResultAdded(new TweakerItemSub(stack.itemID, stack.getItemDamage()));
			}
		}

		public void itemWildcardRemoved(TweakerOreEntry entry, int id) {
			List<ItemStack> subItems = new ArrayList<ItemStack>();
			MineTweakerUtil.getSubItems(id, subItems);
			for (ItemStack stack : subItems) {
				base.onPatternResultRemoved(new TweakerItemSub(stack.itemID, stack.getItemDamage()));
			}
		}
	}
}
