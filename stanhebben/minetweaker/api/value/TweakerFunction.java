package stanhebben.minetweaker.api.value;

import stanhebben.minetweaker.api.TweakerNameSpace;

/**
 * Implements functions. It is recommended that callable functions extend this
 * class.
 * 
 * @author Stan Hebben
 */
public abstract class TweakerFunction extends TweakerValue {
	@Override
	public abstract TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments);
}
