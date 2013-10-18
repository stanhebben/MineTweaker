/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import forestry.api.genetics.AlleleManager;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class PrintAlleleListFunction extends TweakerFunction {
	public static final PrintAlleleListFunction INSTANCE = new PrintAlleleListFunction();
	
	private PrintAlleleListFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		System.out.println("Genes:");
		for (String s : AlleleManager.alleleRegistry.getRegisteredAlleles().keySet()) {
			System.out.println("  " + s);
		}
		return null;
	}

	@Override
	public String toString() {
		return "bees.printAlleles";
	}
}
