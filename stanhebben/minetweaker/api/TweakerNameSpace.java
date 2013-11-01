package stanhebben.minetweaker.api;

import java.util.HashMap;

import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * A namespace contains all symbols inside a certain scope. The root namespace
 * is the global namespace, containing symbols such as minetweaker, recipes,
 * furnaces, oredict, ...
 * 
 * For each new scope (function, statement block, ...) a new instance of this
 * class is created.
 * 
 * @author Stan Hebben
 */
public final class TweakerNameSpace {
	private final HashMap<String, TweakerValue> values;
	private final TweakerNameSpace parent;
	private final boolean fence;
	
	/**
	 * Creates a new namespace with the specified parent namespace. If an item
	 * could not be found in this namespace, it will be looked up in the parent
	 * namespace, recursively.
	 * 
	 * @param parent the parent namespace (null for the global namespace)
	 */
	public TweakerNameSpace(TweakerNameSpace parent) {
		this.parent = parent;
		this.values = new HashMap<String, TweakerValue>();
		this.fence = false;
	}
	
	/**
	 * Creates a new namespace with the specified parent namespace. If an item
	 * could not be found in this namespace, it will be looked up in the parent
	 * namespace, recursively.
	 * 
	 * @param parent the parent namespace (null for the global namespace)
	 * @param fence fence parameter. if true, namespaces with this namespace as parent cannot change values in this namespace
	 */
	public TweakerNameSpace(TweakerNameSpace parent, boolean fence) {
		this.parent = parent;
		this.values = new HashMap<String, TweakerValue>();
		this.fence = fence;
	}
	
	/**
	 * Checks if the specified symbol exists in the namespace.
	 * 
	 * @param name symbol name
	 * @return true if it exists
	 */
	public boolean contains(String name) {
		if (values.containsKey(name)) {
			return true;
		} else if (parent != null) {
			return parent.contains(name);
		} else {
			return false;
		}
	}
	
	/**
	 * Sets an item value. If the item value exists in a parent namespace, then
	 * the parent item value will be set. If the parent namespace is fenced, or
	 * the item was not found, a new item will be set in this namespace instead.
	 * 
	 * @param name item name
	 * @param value item value
	 */
	public void put(String name, TweakerValue value) {
		if (!put2(name, value)) {
			values.put(name, value);
		}
	}
	
	/**
	 * Gets an item value. If the item is not found in this namespace, it will
	 * be looked up in a parent namespace and that one will be returned instead.
	 * If no item could be found up to the root, null will be returned.
	 * 
	 * @param name item name
	 * @return item value, or null
	 */
	public TweakerValue get(String name) {
		if (values.containsKey(name)) {
			return values.get(name);
		} else if (parent != null) {
			return parent.get(name);
		} else {
			return null;
		}
	}
	
	/**
	 * Prints the contents to standard output.
	 */
	public void printContents() {
		for (String key : values.keySet()) {
			System.out.println(key + ": " + values.get(key));
		}
		
		if (parent != null) {
			System.out.println("-- Parent --");
			parent.printContents();
		}
	}
	
	// ---------------------
	// -- Private methods --
	// ---------------------
	
	private boolean put2(String name, TweakerValue value) {
		if (!fence && values.containsKey(name)) {
			values.put(name, value);
			return true;
		} else if (parent != null) {
			return parent.put2(name, value);
		} else {
			return false;
		}
	}
}
