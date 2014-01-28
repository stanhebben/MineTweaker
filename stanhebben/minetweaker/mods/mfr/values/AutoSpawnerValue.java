/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.AutoSpawnerAddBlacklistFunction;
import stanhebben.minetweaker.mods.mfr.function.AutoSpawnerRemoveBlacklistFunction;

/**
 *
 * @author Stanneke
 */
public class AutoSpawnerValue extends TweakerValue {
	public static final AutoSpawnerValue INSTANCE = new AutoSpawnerValue();
	
	private AutoSpawnerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDBLACKLIST:
				return AutoSpawnerAddBlacklistFunction.INSTANCE;
			case REMOVEBLACKLIST:
				return AutoSpawnerRemoveBlacklistFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.autoSpawner";
	}
}
