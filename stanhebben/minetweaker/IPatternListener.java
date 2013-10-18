package stanhebben.minetweaker;

import stanhebben.minetweaker.api.value.TweakerItem;

/**
 * Listens to possible changes in item pattern results.
 * 
 * @author Stan Hebben
 */
public interface IPatternListener {
	/**
	 * Called when an item has become valid for an item pattern.
	 * 
	 * @param item newly available item
	 */
	public void onPatternResultAdded(TweakerItem item);
	
	/**
	 * Called when an item has become invalid for an item pattern.
	 * 
	 * @param item the item that has become unavailable
	 */
	public void onPatternResultRemoved(TweakerItem item);
}
