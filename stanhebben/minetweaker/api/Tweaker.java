package stanhebben.minetweaker.api;

import cpw.mods.fml.common.FMLLog;
import java.util.logging.Level;
import java.util.logging.Logger;
import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;

/**
 * Tweaker is the central mod interface class for MineTweaker.
 * 
 * @author Stan Hebben
 */
public class Tweaker {
	private static final Logger LOGGER = Logger.getLogger("MineTweaker");
	
	private Tweaker() {}
	
	/**
	 * Registers a mod interface. Mod interfaces allow mods to provide their
	 * own functionality to MineTweaker and can be accessed in scripts using
	 * mods.<interface name> .
	 * 
	 * @param iface interface to register
	 */
	public static void registerModInterface(MineTweakerInterface iface) {
		MineTweaker.instance.registerModInterface(iface);
	}
	
	/**
	 * Executes an action. All modification code should be wrapped inside
	 * actions and executed with this method.
	 * 
	 * @param action action to be applied
	 */
	public static void apply(IUndoableAction action) {
		MineTweaker.instance.apply(action);
	}
	
	/**
	 * Removes a pattern from all known crafting methods. Aims to make the
	 * item matching the pattern uncraftable.
	 * 
	 * @param pattern the pattern to match output items with
	 */
	public static void remove(TweakerItemStackPattern pattern) {
		MineTweaker.instance.remove(pattern);
	}
	
	/**
	 * Returns the global namespace. Used to modify the global values. Don't use
	 * this directly to execute function callbacks since local variable would be
	 * stored in here too; use getGlobalWrapped() instead.
	 * 
	 * @return the global namespace
	 */
	public static TweakerNameSpace getGlobal() {
		return MineTweaker.instance.getGlobal();
	}
	
	/**
	 * Returns a wrapped version of the global namespace. Can be used to 
	 * execute function callbacks. Can be used as local namespace: values
	 * written in this namespace will be forgotten afterwards.
	 * 
	 * @return a local version of the global namespace
	 */
	public static TweakerNameSpace getGlobalWrapped() {
		return new TweakerNameSpace(getGlobal());
	}
	
	/**
	 * Logs a message to the MineTweaker logger.
	 * 
	 * @param level logging level
	 * @param message logging message
	 */
	public static void log(Level level, String message) {
		LOGGER.log(level, message);
	}
	
	/**
	 * Logs a message to the MineTweaker logger.
	 * 
	 * @param level logging level
	 * @param message logging message
	 * @param ex exception to be logged
	 */
	public static void log(Level level, String message, Exception ex) {
		LOGGER.log(level, message, ex);
	}
	
	/**
	 * Checks if this logging level is loggable.
	 * 
	 * @param level logging level
	 * @return true if this logging level is being logged
	 */
	public static boolean isLoggable(Level level) {
		return LOGGER.isLoggable(level);
	}
	
	// ####################
	// ## Static Methods ##
	// ####################
	
	/**
	 * Called internally upon initialization.
	 */
	public static void onInit() {
		LOGGER.setParent(FMLLog.getLogger());
	}
}
