/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.mods.forestry.values.ForestryValue;

/**
 *
 * @author Stanneke
 */
public class ForestrySupport extends MineTweakerInterface {
	public static final ForestrySupport INSTANCE = new ForestrySupport();

	private ForestrySupport() {
		super("forestry", ForestryValue.INSTANCE);
	}
}
