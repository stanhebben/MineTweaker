/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.SafariNetAddBlacklistFunction;
import stanhebben.minetweaker.mods.mfr.function.SafariNetRemoveBlacklistFunction;

/**
 *
 * @author Stanneke
 */
public class SafariNetValue extends TweakerValue {
	public static final SafariNetValue INSTANCE = new SafariNetValue();
	
	private SafariNetValue() {}

	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDBLACKLIST:
				return SafariNetAddBlacklistFunction.INSTANCE;
			case REMOVEBLACKLIST:
				return SafariNetRemoveBlacklistFunction.INSTANCE;
		}
		return index(index);
	}
	
	@Override
	public String toString() {
		return "mfr.safariNet";
	}
}
