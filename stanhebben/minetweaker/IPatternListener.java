/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker;

import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public interface IPatternListener {
	public void onPatternResultAdded(TweakerItem item);
	
	public void onPatternResultRemoved(TweakerItem item);
}
