/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.util;

import java.util.Iterator;
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
		Iterator<TweakerItem> matches = pattern.getMatches();
		while (matches.hasNext()) {
			onAdded(matches.next());
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
