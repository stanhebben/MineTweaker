/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.functions.AddCoolantFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.ContainsCoolantFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.RemoveCoolantFunction;

/**
 *
 * @author Stanneke
 */
public class CoolantsValue extends TweakerValue {
	public static final CoolantsValue INSTANCE = new CoolantsValue();
	
	private CoolantsValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADD:
				return AddCoolantFunction.INSTANCE;
			case REMOVE:
				return RemoveCoolantFunction.INSTANCE;
			case CONTAINS:
				return ContainsCoolantFunction.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in buildcraft.coolants: " + index);
	}

	@Override
	public String toString() {
		return "buildcraft.coolants";
	}
}
