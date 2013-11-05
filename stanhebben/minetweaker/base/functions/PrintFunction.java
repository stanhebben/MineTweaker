/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.functions;

import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerString;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class PrintFunction extends TweakerFunction {
	public static final PrintFunction INSTANCE = new PrintFunction();
	
	private PrintFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("Print should receive at least one argument");
		} else if (arguments.length == 1) {
			if (arguments[0] == null) {
				Tweaker.log(Level.INFO, "null");
			} else {
				TweakerString asString = arguments[0].asString();
				if (asString == null) {
					Tweaker.log(Level.INFO, arguments[0].toString());
				} else {
					Tweaker.log(Level.INFO, asString.get());
				}
			}
		} else {
			StringBuilder builder = new StringBuilder();
			for (TweakerValue argument : arguments) {
				if (argument == null) {
					builder.append("null");
				} else {
					TweakerString asString = argument.asString();
					if (asString == null) {
						builder.append("?");
					} else {
						builder.append(asString.get());
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:print";
	}
}
