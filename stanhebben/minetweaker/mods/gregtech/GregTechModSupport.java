/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.mods.gregtech.values.GregTechValue;

/**
 *
 * @author Stanneke
 */
public class GregTechModSupport extends MineTweakerInterface {
	public static final GregTechModSupport INSTANCE = new GregTechModSupport();
	
	private GregTechModSupport() {
		super("gregtech", GregTechValue.INSTANCE);
	}

	@Override
	public String toString() {
		return "modSupport.gregtech";
	}
}
