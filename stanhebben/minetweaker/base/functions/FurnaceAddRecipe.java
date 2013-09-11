package stanhebben.minetweaker.base.functions;

import java.util.Arrays;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import static stanhebben.minetweaker.api.value.TweakerValue.notNull;
import stanhebben.minetweaker.base.actions.FurnaceAddRecipeAction;
import stanhebben.minetweaker.base.actions.FurnaceRemoveMetaRecipeAction;
import stanhebben.minetweaker.util.ItemPatternTransformer;

public class FurnaceAddRecipe extends TweakerFunction {
	public static final FurnaceAddRecipe INSTANCE = new FurnaceAddRecipe();
	
	private FurnaceAddRecipe() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("furnace.addRecipe requires at least two arguments");
		}
		TweakerItemStack output =
				notNull(arguments[0], "furnace recipe output cannot be null")
				.toItemStack("furnace recipe output must be an item stack");
		float xp = 0;
		if (arguments.length >= 3) {
			xp = notNull(arguments[2], "furnace recipe xp cannot be null")
					.toFloat("furnace recipe xp must be a float value")
					.get();
		}
		TweakerValue input =
				notNull(arguments[1], "furnace recipe input cannot be null");
		if (input.asItem() != null) {
			Tweaker.apply(new FurnaceAddRecipeAction(output, input.asItem(), xp));
		} else if (input.asItemPattern() != null) {
			final float fxp = xp;
			final TweakerItemStack foutput = output;
			
			ItemPatternTransformer transformer = new ItemPatternTransformer(input.asItemPattern()) {
				@Override
				public void onAdded(TweakerItem item) {
					Tweaker.apply(new FurnaceAddRecipeAction(foutput, item, fxp));
				}

				@Override
				public void onRemoved(TweakerItem item) {
					Tweaker.apply(new FurnaceRemoveMetaRecipeAction(Arrays.asList(item.getItemId(), item.getItemSubId())));
				}
			};
			transformer.init();
		}
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:furnace.addRecipe";
	}
}
