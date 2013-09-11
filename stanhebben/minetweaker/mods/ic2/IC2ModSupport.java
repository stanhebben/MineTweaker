//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.mods.ic2.functions.CompressorRemoveFunction;
import stanhebben.minetweaker.mods.ic2.functions.ExtractorRemoveFunction;
import stanhebben.minetweaker.mods.ic2.functions.MaceratorRemoveFunction;
import stanhebben.minetweaker.mods.ic2.values.IC2Value;

/**
 *
 * @author Stanneke
 */
public class IC2ModSupport extends MineTweakerInterface {
	public static final IC2ModSupport INSTANCE = new IC2ModSupport();
	
	private IC2ModSupport() {
		super("ic2", IC2Value.INSTANCE);
	}
	
	@Override
	public int remove(TweakerItemStackPattern pattern) {
		int total = 0;
		total += CompressorRemoveFunction.INSTANCE.remove(pattern);
		total += ExtractorRemoveFunction.INSTANCE.remove(pattern);
		total += MaceratorRemoveFunction.INSTANCE.remove(pattern);
		return total;
	}
}
