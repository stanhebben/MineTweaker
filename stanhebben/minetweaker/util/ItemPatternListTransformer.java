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
public abstract class ItemPatternListTransformer {
	private TweakerItemPattern[] patterns;
	
	public ItemPatternListTransformer(TweakerItemPattern... patterns) {
		this.patterns = patterns;
		
		TweakerItem[] items = new TweakerItem[patterns.length];
		collectAdded(items, 0, -1);
		
		for (int i = 0; i < patterns.length; i++) {
			patterns[i].addListener(new PatternListener(i));
		}
	}
	
	public abstract void onAdded(TweakerItem... items);
	
	public abstract void onRemoved(TweakerItem... items);
	
	private void collectAdded(TweakerItem[] items, int index, int skip) {
		if (index == items.length) {
			onAdded(items);
		} else if (index == skip) {
			collectAdded(items, index + 1, skip);
		} else {
			Iterator<TweakerItem> matches = patterns[index].getMatches();
			while (matches.hasNext()) {
				items[index] = matches.next();
				collectAdded(items, index + 1, skip);
			}
		}
	}
	
	private void collectRemoved(TweakerItem[] items, int index, int skip) {
		if (index == items.length) {
			onAdded(items);
		} else if (index == skip) {
			collectAdded(items, index + 1, skip);
		} else {
			Iterator<TweakerItem> matches = patterns[index].getMatches();
			while (matches.hasNext()) {
				items[index] = matches.next();
				collectAdded(items, index + 1, skip);
			}
		}
	}
	
	private class PatternListener implements IPatternListener {
		private int index;
		
		public PatternListener(int index) {
			this.index = index;
		}

		public void onPatternResultAdded(TweakerItem item) {
			TweakerItem[] items = new TweakerItem[patterns.length];
			items[index] = item;
			collectAdded(items, 0, index);
		}

		public void onPatternResultRemoved(TweakerItem item) {
			TweakerItem[] items = new TweakerItem[patterns.length];
			items[index] = item;
			collectRemoved(items, 0, index);
		}
	}
}
