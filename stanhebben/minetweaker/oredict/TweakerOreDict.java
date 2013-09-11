/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.oredict;

import java.util.HashMap;

/**
 *
 * @author Stanneke
 */
public class TweakerOreDict {
	private static final HashMap<String, TweakerOreEntry> entries = new HashMap<String, TweakerOreEntry>();
	
	private TweakerOreDict() {}
	
	public static TweakerOreEntry getEntry(String name) {
		if (!entries.containsKey(name)) {
			entries.put(name, new TweakerOreEntry(name));
		}
		return entries.get(name);
	}
}
