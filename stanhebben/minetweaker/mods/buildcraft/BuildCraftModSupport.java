/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.mods.buildcraft.functions.AssemblyTableRemoveFunction;
import stanhebben.minetweaker.mods.buildcraft.values.BuildCraftValue;

/**
 *
 * @author Stanneke
 */
public class BuildCraftModSupport extends MineTweakerInterface {
	public static final BuildCraftModSupport INSTANCE = new BuildCraftModSupport();
	
	private BuildCraftModSupport() {
		super("buildcraft", BuildCraftValue.INSTANCE);
	}
	
	@Override
	public int remove(TweakerItemStackPattern pattern) {
		return AssemblyTableRemoveFunction.INSTANCE.remove(pattern);
	}
}
