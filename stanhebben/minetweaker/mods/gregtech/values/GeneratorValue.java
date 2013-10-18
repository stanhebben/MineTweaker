/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.functions.GeneratorAddFuelFunction;

/**
 *
 * @author Stanneke
 */
public class GeneratorValue extends TweakerValue {
	public static final GeneratorValue INSTANCE_DIESEL = new GeneratorValue(GeneratorAddFuelFunction.INSTANCE_DIESEL);
	public static final GeneratorValue INSTANCE_GASTURBINE = new GeneratorValue(GeneratorAddFuelFunction.INSTANCE_GASTURBINE);
	public static final GeneratorValue INSTANCE_THERMAL = new GeneratorValue(GeneratorAddFuelFunction.INSTANCE_THERMAL);
	public static final GeneratorValue INSTANCE_DENSEFLUID = new GeneratorValue(GeneratorAddFuelFunction.INSTANCE_DENSEFLUID);
	public static final GeneratorValue INSTANCE_PLASMA = new GeneratorValue(GeneratorAddFuelFunction.INSTANCE_PLASMA);
	public static final GeneratorValue INSTANCE_MAGIC = new GeneratorValue(GeneratorAddFuelFunction.INSTANCE_MAGIC);
	
	private final GeneratorAddFuelFunction addFuelFunction;
	
	private GeneratorValue(GeneratorAddFuelFunction addFuelFunction) {
		this.addFuelFunction = addFuelFunction;
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFUEL:
				return addFuelFunction;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "gregtech.generator";
	}
}
