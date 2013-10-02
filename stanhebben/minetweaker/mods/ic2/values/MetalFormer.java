//#fileifndef OLDIC2

package stanhebben.minetweaker.mods.ic2.values;

import ic2.api.recipe.Recipes;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class MetalFormer extends TweakerValue {
	public static final MetalFormer INSTANCE = new MetalFormer();
	
	private static final MachineValue CUTTING = new MachineValue(Recipes.metalformerCutting, "metal former cutting");
	private static final MachineValue EXTRUDING = new MachineValue(Recipes.metalformerExtruding, "metal former extruding");
	private static final MachineValue ROLLING = new MachineValue(Recipes.metalformerRolling, "metal former rolling");
	
	private MetalFormer() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case CUTTING:
				return CUTTING;
			case EXTRUDING:
				return EXTRUDING;
			case ROLLING:
				return ROLLING;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "ic2.metalformer";
	}
}
