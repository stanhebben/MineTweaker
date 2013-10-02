/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.util;

import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemPattern;

/**
 *
 * @author Stanneke
 */
public abstract class ItemPatternTransformer implements IPatternListener {
	private TweakerItemPattern pattern;
	
	public ItemPatternTransformer(TweakerItemPattern pattern) {
		this.pattern = pattern;
	}
	
	public final void init() {
		for (TweakerItem item : pattern.getMatches()) {
			onAdded(item);
		}
		pattern.addListener(this);
	}
	
	public abstract void onAdded(TweakerItem item);
	
	public abstract void onRemoved(TweakerItem item);

	public void onPatternResultAdded(TweakerItem item) {
		onAdded(item);
	}

	public void onPatternResultRemoved(TweakerItem item) {
		onRemoved(item);
	}
}
