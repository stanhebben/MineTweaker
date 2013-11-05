/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.RancherAddRanchableFunction;
import stanhebben.minetweaker.mods.mfr.function.RancherRemoveRanchableFunction;

/**
 *
 * @author Stanneke
 */
public class RancherValue extends TweakerValue {
	public static final RancherValue INSTANCE = new RancherValue();
	
	private RancherValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRANCHABLE:
				return RancherAddRanchableFunction.INSTANCE;
			case REMOVERANCHABLE:
				return RancherRemoveRanchableFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.rancher";
	}
}
