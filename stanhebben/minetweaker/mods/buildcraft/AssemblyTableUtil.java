/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft;

import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableUtil {
	private AssemblyTableUtil() {}
	
	public static boolean matches(ItemStack[] recipe, TweakerItemStackPattern[] pattern, boolean wildcard) {
		if (wildcard) {
			if (pattern.length > recipe.length) return false;
		} else {
			if (pattern.length != recipe.length) return false;
		}
		
		boolean[] tag = new boolean[recipe.length];
		outer: for (TweakerItemStackPattern pi : pattern) {
			for (int i = 0; i < recipe.length; i++) {
				if (tag[i]) continue;
				if (pi.matches(recipe[i])) {
					tag[i] = true;
					continue outer;
				}
			}
			return false;
		}
		
		return true;
	}
}
