/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.base.values;

import stanhebben.minetweaker.MineTweakerRegistry;
import stanhebben.minetweaker.api.value.TweakerItemSimple;
import stanhebben.minetweaker.api.value.TweakerString;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class DollarValue extends TweakerValue {
	@Override
	public TweakerValue index(TweakerValue index) {
		if (index.getClass() == TweakerString.class) return index(index);
		if (index.asInt() != null) {
			return index(index.asInt().get());
		}
		return super.index(index);
	}
	
	@Override
	public TweakerValue index(int index) {
		return new TweakerItemSimple(index);
	}
	
	@Override
	public TweakerValue index(String index) {
		return MineTweakerRegistry.INSTANCE.getAny(index);
	}

	@Override
	public String toString() {
		return "$";
	}
}
