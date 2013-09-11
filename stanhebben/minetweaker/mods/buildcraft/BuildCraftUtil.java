/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft;

//#ifdef MC152
import buildcraft.api.recipes.RefineryRecipe;
//#else
//+import buildcraft.api.recipes.RefineryRecipes;
//#endif
import stanhebben.minetweaker.api.Tweaker;
import java.util.SortedSet;
import java.util.logging.Level;
import stanhebben.minetweaker.MineTweakerUtil;

/**
 *
 * @author Stanneke
 */
public class BuildCraftUtil {
	//#ifdef MC152
	private static SortedSet<RefineryRecipe> refineryRecipes = null;
	//#else
	//+private static SortedSet<RefineryRecipes.Recipe> refineryRecipes = null;
	//#endif
	
	static {
		//#ifdef MC152
		refineryRecipes = MineTweakerUtil.getPrivateStaticObject(RefineryRecipe.class, "recipes");
		//#else
		//+refineryRecipes = MineTweakerUtil.getPrivateStaticObject(RefineryRecipes.class, "recipes");
		//#endif
		if (refineryRecipes == null) {
			Tweaker.log(Level.SEVERE, "Could not get the RefineryRecipes recipes field, cannot remove or undo refinery recipes");
		}
	}
	
	private BuildCraftUtil() {}
	
	//#ifdef MC152
	public static SortedSet<RefineryRecipe> getRefineryRecipes() {
		return refineryRecipes;
	}
	//#else
	//+public static SortedSet<RefineryRecipes.Recipe> getRefineryRecipes() {
		//+return refineryRecipes;
	//+}
	//#endif
}
