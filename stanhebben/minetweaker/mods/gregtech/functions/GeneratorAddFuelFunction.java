/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.actions.GeneratorAddFuelAction;

/**
 *
 * @author Stanneke
 */
public class GeneratorAddFuelFunction extends TweakerFunction {
	public static final GeneratorAddFuelFunction INSTANCE_DIESEL = new GeneratorAddFuelFunction(0);
	public static final GeneratorAddFuelFunction INSTANCE_GASTURBINE = new GeneratorAddFuelFunction(1);
	public static final GeneratorAddFuelFunction INSTANCE_THERMAL = new GeneratorAddFuelFunction(2);
	public static final GeneratorAddFuelFunction INSTANCE_DENSEFLUID = new GeneratorAddFuelFunction(3);
	public static final GeneratorAddFuelFunction INSTANCE_PLASMA = new GeneratorAddFuelFunction(4);
	public static final GeneratorAddFuelFunction INSTANCE_MAGIC = new GeneratorAddFuelFunction(5);
	
	private final int type;
	
	private GeneratorAddFuelFunction(int type) {
		this.type = type;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) throw new TweakerExecuteException("addFuel requires at least 2 arguments");
		TweakerItemStack input =
				notNull(arguments[0], "fuel cannot be null")
				.toItemStack("fuel must be an item (fluid container)");
		int euPerMB =
				notNull(arguments[1], "eu per millibucket cannot be null")
				.toInt("eu per millibucket must be an int").get();
		TweakerItemStack output = arguments.length < 3 || arguments[2] == null ? null :
				arguments[2].toItemStack("output must be an item");
		
		Tweaker.apply(new GeneratorAddFuelAction(type, input, euPerMB, output));
		return null;
	}

	@Override
	public String toString() {
		return "generator.addFuel";
	}
}
