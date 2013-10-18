package stanhebben.minetweaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//#ifdef OLDLIQUIDS
//+import net.minecraftforge.liquids.LiquidDictionary;
//#else
import net.minecraftforge.fluids.FluidRegistry;
//#endif
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemSimple;
import stanhebben.minetweaker.api.value.TweakerItemSub;

/**
 * The registry contains a list of all valid block names, item names and all
 * valid block, item and fluid prefixes.
 * 
 * @author Stan Hebben
 */
public class MineTweakerRegistry {
	public static final MineTweakerRegistry INSTANCE = new MineTweakerRegistry();
	
	private MineTweakerRegistry() {
		blockPrefixes = new HashSet<String>();
		blocksByName = new HashMap<String, TweakerItem>();
		itemPrefixes = new HashSet<String>();
		itemsByName = new HashMap<String, TweakerItem>();
		allItems = new ArrayList<TweakerItem>();
		fluidPrefixes = new HashSet<String>();
	}
	
	private final HashSet<String> itemPrefixes;
	private final HashMap<String, TweakerItem> itemsByName;
	private final ArrayList<TweakerItem> allItems;
	private final HashSet<String> blockPrefixes;
	private final HashMap<String, TweakerItem> blocksByName;
	private final HashSet<String> fluidPrefixes;
	
	public void init() {
		// load items
		try {
			for (Item item : Item.itemsList) {
				if (item == null) continue;
				
				TweakerItemSimple allValue = new TweakerItemSimple(item.itemID);
				if (allValue.getName() != null) {
					collectPrefixes(allValue.getName(), itemPrefixes);
					itemsByName.put(allValue.getName(), allValue);
					allItems.add(allValue);
				}
				
				if (item.getHasSubtypes()) {
					try {
						List<ItemStack> subItems = new ArrayList<ItemStack>();
						MineTweakerUtil.getSubItems(item.itemID, subItems);
						for (ItemStack subItem : subItems) {
							TweakerItemSub value = new TweakerItemSub(subItem.itemID, subItem.getItemDamage());
							String name = value.getName();
							if (name == null) continue;
							collectPrefixes(value.getName(), itemPrefixes);
							itemsByName.put(value.getName(), value);
							allItems.add(value);
						}
					} catch (TweakerExecuteException ex) {
						Tweaker.log(Level.WARNING, "Exception loading " + item.getUnlocalizedName() + " subitems: "  + ex.getMessage());
					} catch (Exception ex) {
						Tweaker.log(Level.WARNING, "Exception loading " + item.getUnlocalizedName() + " subitems: "  + ex.getMessage());
					}
				}
			}
		} catch (Exception ex) {
			Tweaker.log(Level.SEVERE, "Exception loading items", ex);
		}
		
		// load blocks
		try {
			for (Block block : Block.blocksList) {
				if (block == null || block.blockID == 0) continue;
				if (Item.itemsList[block.blockID] == null) continue;
				
				TweakerItemSimple allValue = new TweakerItemSimple(block.blockID);
				if (allValue.getName() != null) {
					collectPrefixes(allValue.getName(), blockPrefixes);
					blocksByName.put(allValue.getName(), allValue);
				}
				
				if (Item.itemsList[block.blockID].getHasSubtypes()) {
					try {
						List<ItemStack> subBlocks = new ArrayList<ItemStack>();
						MineTweakerUtil.getSubBlocks(block.blockID, subBlocks);
						for (ItemStack subBlock : subBlocks) {
							TweakerItemSub value = new TweakerItemSub(block.blockID, subBlock.getItemDamage());
							String name = value.getName();
							if (name == null) continue;
							collectPrefixes(value.getName(), blockPrefixes);
							blocksByName.put(value.getName(), value);
						}
					} catch (TweakerExecuteException ex) {
						Tweaker.log(Level.WARNING, "Exception loading " + allValue.getName() + " subitems: "  + ex.getMessage());
					} catch (Exception ex) {
						Tweaker.log(Level.WARNING, "Exception loading " + allValue.getName() + " subitems: "  + ex.getMessage());
					}
				}
			}
		} catch (Exception ex) {
			Tweaker.log(Level.SEVERE, "Exception loading blocks", ex);
		}
		
		//#ifdef OLDLIQUIDS
		//+for (String name : LiquidDictionary.getLiquids().keySet()) {
			//+collectPrefixes(name, fluidPrefixes);
		//+}
		//#else
		for (String name : FluidRegistry.getRegisteredFluidIDs().keySet()) {
			collectPrefixes(name, fluidPrefixes);
		}
		//#endif
	}
	
	public TweakerItem getBlock(String name) {
		return blocksByName.get(name);
	}
	
	public boolean isBlockPrefix(String value) {
		return blockPrefixes.contains(value);
	}
	
	public TweakerItem getItem(String name) {
		return itemsByName.get(name);
	}
	
	public boolean isItemPrefix(String value) {
		return itemPrefixes.contains(value);
	}
	
	public TweakerItem getAny(String name) {
		if (blocksByName.containsKey(name)) {
			return blocksByName.get(name);
		} else {
			return itemsByName.get(name);
		}
	}
	
	public boolean isFluidPrefix(String value) {
		return fluidPrefixes.contains(value);
	}
	
	public List<TweakerItem> getAllItems() {
		return allItems;
	}
	
	private void collectPrefixes(String value, HashSet<String> prefixes) {
		int dot = value.lastIndexOf('.');
		if (dot > 0) {
			String prefix = value.substring(0, dot);
			if (!prefixes.contains(prefix)) {
				prefixes.add(prefix);
				collectPrefixes(prefix, prefixes);
			}
		}
	}
}
