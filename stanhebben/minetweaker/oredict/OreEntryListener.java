/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.oredict;

import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public interface OreEntryListener {
	public void itemAdded(TweakerOreEntry entry, TweakerItem item);
	
	public void itemWildcardAdded(TweakerOreEntry entry, int id);
	
	public void itemRemoved(TweakerOreEntry entry, TweakerItem item);
	
	public void itemWildcardRemoved(TweakerOreEntry entry, int id);
}
