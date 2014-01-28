/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.railcraft;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.mods.railcraft.values.RailcraftValue;

/**
 *
 * @author Stanneke
 */
public class RailcraftModSupport extends MineTweakerInterface {
	public static final RailcraftModSupport INSTANCE = new RailcraftModSupport();
	
	private RailcraftModSupport() {
		super("railcraft", RailcraftValue.INSTANCE);
	}
}
