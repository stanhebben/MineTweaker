package stanhebben.minetweaker.api;

import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Used by mods to register their own interface.
 * 
 * Mods can do the following:
 * - Supply a table of methods that scripts can call.
 * - Act on the remove command.
 * 
 * The most important function is the mod value, which is a TweakerValue. By
 * subclassing TweakerValue and implementing the index(String) method, scripts
 * can retrieve values (which can then be callable functions, constants, subtables ...)
 * and use those to interact with the mod. By implementing indexSet(String, TweakerValue)
 * they can also make certain values settable.
 * 
 * Note that whenever a function want to change something, it must wrap that
 * modification code in an IUndoableAction and supply it to the Tweaker with
 * Tweaker.apply().
 * 
 * @author Stan Hebben
 */
public class MineTweakerInterface {
	private final String name;
	private final TweakerValue value;
	
	/**
	 * Constructs a new interface with the specified name and value. The value
	 * will be registered in the mods table under the specified name. It is
	 * highly recommended to use the mod's name as name.
	 * 
	 * @param name mod name
	 * @param value mod interface value
	 */
	public MineTweakerInterface(String name, TweakerValue value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Returns the name of the mod interface.
	 * 
	 * @return mod interface name
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Returns the value of the mod interface. A mod interface subclasses
	 * TweakerValue and should implement at least the index(String) method to
	 * offer actual functionality. It may implement indexSet(String) too.
	 * 
	 * @return mod interface value
	 */
	public final TweakerValue getValue() {
		return value;
	}
	
	/**
	 * Allows mods to remove additional recipes on the minetweaker.remove command.
	 * Subclasses of this class can implement this method to remove recipes in
	 * its own machines. Any recipes matching with this item stack pattern should
	 * be removed. (the goal is to make the item uncraftable)
	 * 
	 * Note that no actual items should ever be removed, only the recipes that
	 * craft them.
	 * 
	 * @param item item pattern to be removed
	 * @return the number of recipes that have been removed
	 */
	public int remove(TweakerItemStackPattern item) {
		return 0;
	}
	
	/**
	 * Allows mods to replace items on the minetweaker.replace command.
	 * Subclasses of this class can implement this method to replace items in
	 * its own machines. Any items in recipes matching with this item stack
	 * pattern should be replaced. (the goal is to replace each and every
	 * such recipe item with the new item)
	 * 
	 * Note that no actual items should ever be replaced, only the recipes should
	 * be altered. The replaced TweakerValue will be a valid recipe item. This could
	 * be an ore item or item value. If your machines do not support ore items,
	 * you may ignore the command.
	 * 
	 * @param original original item pattern
	 * @param replaced replaced item
	 * @return the number of occurrences replaced
	 */
	public int replace(TweakerItemPattern original, TweakerValue replaced) {
		return 0;
	}
}
