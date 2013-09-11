/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

/**
 *
 * @author Stanneke
 */
public class TweakerNull extends TweakerValue {
	public static final TweakerNull INSTANCE = new TweakerNull();
	
	private TweakerNull() {}

	@Override
	public boolean equals(TweakerValue value) {
		return value == null || value == this;
	}
	
	@Override
	public String toString() {
		return "null";
	}
}
