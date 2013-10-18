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
public final class StackPatternRecipeInput implements IRecipeInput {
	private final TweakerItemStackPattern pattern;
	private final List<ItemStack> matches;
	
	public StackPatternRecipeInput(TweakerItemStackPattern pattern) {
		this.pattern = pattern;
		
		matches = new ArrayList<ItemStack>();
		for (TweakerItem item : pattern.getMatches()) {
			matches.add(item.make(1));
		}
	}
	
	public boolean matches(ItemStack subject) {
		return pattern.matches(subject);
	}

	public int getAmount() {
		return pattern.getAmount();
	}

	public List<ItemStack> getInputs() {
		return matches;
	}
}
