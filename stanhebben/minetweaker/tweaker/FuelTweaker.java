package stanhebben.minetweaker.tweaker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelTweaker {
	public static final FuelTweaker INSTANCE = new FuelTweaker();
	
	private List<IFuelHandler> original;
	private final List<SetFuelPattern> fuelPatterns = new ArrayList<SetFuelPattern>();
	private final HashMap<String, Integer> fuelItems = new HashMap<String, Integer>();
	
	private FuelTweaker() {}

	public void register() {
		try {
			Field fuelHandlers = GameRegistry.class.getDeclaredField("fuelHandlers");
			fuelHandlers.setAccessible(true);
			original = (List<IFuelHandler>) fuelHandlers.get(null);
			List<IFuelHandler> modified = new ArrayList<IFuelHandler>();
			modified.add(new OverridingFuelHandler());
			fuelHandlers.set(null, modified);
		} catch (NoSuchFieldException ex) {
			System.out.println("[MineTweaker] Error: could not get GameRegistry fuel handlers field. Cannot use custom fuel values.");
		} catch (SecurityException ex) {
			System.out.println("[MineTweaker] Error: could not alter GameRegistry fuel handlers field. Cannot use custom fuel values.");
		} catch (IllegalAccessException ex) {
			System.out.println("[MineTweaker] Error: could not alter GameRegistry fuel handlers field. Cannot use custom fuel values.");
		}
	}
	
	public void addFuelPattern(SetFuelPattern pattern) {
		fuelPatterns.add(pattern);
	}
	
	public void removeFuelPattern(SetFuelPattern pattern) {
		fuelPatterns.remove(pattern);
	}

	public Integer setFuelItem(int itemId, Integer value) {
		if (value == null) {
			return fuelItems.remove(Integer.toString(itemId));
		} else {
			return fuelItems.put(Integer.toString(itemId), value);
		}
	}
	
	public Integer setFuelItem(int itemId, int meta, Integer value) {
		if (value == null) {
			return fuelItems.remove(itemId + ":" + meta);
		} else {
			return fuelItems.put(itemId + ":" + meta, value);
		}
	}
	
	private class OverridingFuelHandler implements IFuelHandler {
		@Override
		public int getBurnTime(ItemStack fuel) {
			if (fuelItems.size() > 0) {
				String key = Integer.toString(fuel.itemID);
				if (fuelItems.containsKey(key)) {
					return fuelItems.get(key);
				} else if (fuel.getHasSubtypes()) {
					key = fuel.itemID + ":" + fuel.getItemDamage();
					if (fuelItems.containsKey(key)) {
						return fuelItems.get(key);
					}
				}
			}
			for (SetFuelPattern override : fuelPatterns) {
				if (override.getPattern().matches(fuel)) {
					return override.getValue();
				}
			}
			
			int max = 0;
			for (IFuelHandler handler : original) {
				max = Math.max(max, handler.getBurnTime(fuel));
			}
			return max;
		}
	}
}
