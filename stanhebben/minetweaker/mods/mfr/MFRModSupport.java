/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.mods.mfr.values.MFRValue;

/**
 *
 * @author Stanneke
 */
public class MFRModSupport extends MineTweakerInterface {
	public static final MFRModSupport INSTANCE = new MFRModSupport();
	
	private MFRModSupport() {
		super("mfr", MFRValue.INSTANCE);
	}
}
