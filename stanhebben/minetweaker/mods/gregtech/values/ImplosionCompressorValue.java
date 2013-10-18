/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.functions.ImplosionCompressorAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class ImplosionCompressorValue extends TweakerValue {
	public static final ImplosionCompressorValue INSTANCE = new ImplosionCompressorValue();
	
	private ImplosionCompressorValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return ImplosionCompressorAddRecipeFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "gregtech.implosionCompressor";
	}
}
