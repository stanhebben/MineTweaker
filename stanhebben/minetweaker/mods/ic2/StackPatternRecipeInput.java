//#fileifndef OLDIC2

package stanhebben.minetweaker.mods.ic2;

import ic2.api.recipe.IRecipeInput;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;

/**
 *
 * @author Stanneke
 */
public class StackPatternRecipeInput implements IRecipeInput {
	private TweakerItemStackPattern pattern;
	
	public StackPatternRecipeInput(TweakerItemStackPattern pattern) {
		this.pattern = pattern;
	}
	
	public boolean matches(ItemStack subject) {
		return pattern.matches(subject);
	}

	public int getAmount() {
		return pattern.getAmount();
	}

	public List<ItemStack> getInputs() {
		List<ItemStack> result = new ArrayList<ItemStack>();
		for (TweakerItem item : pattern.getMatches()) {
			result.add(item.make(1));
		}
		return result;
	}
}
