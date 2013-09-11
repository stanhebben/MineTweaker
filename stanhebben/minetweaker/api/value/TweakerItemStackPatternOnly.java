/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class TweakerItemStackPatternOnly extends TweakerItemStackPattern {
	private TweakerItemStackPattern base;
	private TweakerValue test;
	
	public TweakerItemStackPatternOnly(TweakerItemStackPattern base, TweakerValue test) {
		this.base = base;
		this.test = test;
	}

	@Override
	public boolean matches(ItemStack item) {
		return base.matches(item) && test.call(Tweaker.getGlobalWrapped(), new TweakerItemStack(item)).toBasicBool();
	}

	@Override
	public String toPatternString() {
		return base.toPatternString() + ".only(<function>);";
	}

	@Override
	public String toString() {
		return "pattern:" + toPatternString();
	}
}
