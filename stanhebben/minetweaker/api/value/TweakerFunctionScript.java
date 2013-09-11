package stanhebben.minetweaker.api.value;

import java.util.Arrays;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.script.statements.TweakerStatement;

/**
 * Script implementation of functions. Created when a function literal is defined
 * in a script.
 * 
 * @author Stan Hebben
 */
public final class TweakerFunctionScript extends TweakerFunction {
	private final String[] arguments;
	private final TweakerStatement[] contents;
	
	public TweakerFunctionScript(String[] arguments, TweakerStatement[] contents) {
		this.arguments = arguments;
		this.contents = contents;
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace global, TweakerValue... arguments) {
		TweakerNameSpace local = new TweakerNameSpace(global, true);
		local.put("args", new TweakerArrayValue(Arrays.asList(arguments)));
		for (int i = 0; i < Math.min(this.arguments.length, arguments.length); i++) {
			local.put(this.arguments[i], arguments[i]);
		}
		
		for (TweakerStatement statement : contents) {
			TweakerValue result = statement.execute(local);
			if (result != null) return result;
		}
		return null;
	}

	@Override
	public String toString() {
		return "<ScriptedFunction>";
	}
}
