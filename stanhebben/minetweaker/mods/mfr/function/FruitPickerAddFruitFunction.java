/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import powercrystals.minefactoryreloaded.api.IFactoryFruit;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.FruitPickerAddFruitAction;

/**
 *
 * @author Stanneke
 */
public class FruitPickerAddFruitFunction extends TweakerFunction {
	public static final FruitPickerAddFruitFunction INSTANCE = new FruitPickerAddFruitFunction();
	private static final Random RANDOM = new Random();
	
	private FruitPickerAddFruitFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		// Arguments:
		// - block
		// - replacement block
		// - possible drops (optional)
		// - chances of possible drops (optional)
		if (arguments.length < 2 || arguments.length > 4) throw new TweakerExecuteException("fruitPicker.addFruit requires 1-3 arguments");
		
		TweakerItem block =
				notNull(arguments[0], "harvestable cannot be null")
				.toItem("harvestable block must be an item");
		TweakerItem replacement =
				notNull(arguments[1], "replacement cannot be null")
				.toItem("replacement block must be an item");
		ItemStack[] possibleDrops;
		if (arguments.length < 3 || arguments[2] == null) {
			possibleDrops = new ItemStack[0];
		} else if (arguments[2].asArray() != null) {
			TweakerArray drops = arguments[2].asArray();
			possibleDrops = new ItemStack[drops.size()];
			
			for (int i = 0; i < possibleDrops.length; i++) {
				possibleDrops[i] =
						notNull(drops.get(i), "drops cannot be null")
						.toItemStack("drops must be items").get();
			}
		} else {
			possibleDrops = new ItemStack[] {
				arguments[2].toItemStack("drop must be an item stack or array of item stacks").get()
			};
		}
		
		float[] dropChances;
		if (arguments.length < 4 || arguments[2] == null) {
			dropChances = new float[0];
		} else if (arguments[3].asArray() != null) {
			TweakerArray chances = arguments[3].asArray();
			dropChances = new float[chances.size()];
			
			for (int i = 0; i < dropChances.length; i++) {
				dropChances[i] =
						notNull(chances.get(i), "drop chance cannot be null")
						.toFloat("drop chance must be a float").get();
			}
		} else {
			dropChances = new float[] {
				arguments[3].toFloat("drop chance must be a float or array of floats").get()
			};
		}
		
		Tweaker.apply(new FruitPickerAddFruitAction(block, new TweakerFruit(
				block.getItemId(), block.getItemSubId(), replacement.make(1),
				possibleDrops, dropChances)));
		return null;
	}

	@Override
	public String toString() {
		return "harvester.addHarvestable";
	}
	
	private static class TweakerFruit implements IFactoryFruit {
		private final int plantId;
		private final int meta;
		private final ItemStack replacement;
		private final ItemStack[] possibleDrops;
		private final float[] chances;
		
		public TweakerFruit(int plantId, int meta, ItemStack replacement, ItemStack[] possibleDrops, float[] chances) {
			this.plantId = plantId;
			this.meta = meta;
			this.replacement = replacement;
			this.possibleDrops = possibleDrops;
			this.chances = chances;
		}

		public int getSourceBlockId() {
			return plantId;
		}

		public boolean canBePicked(World world, int x, int y, int z) {
			return meta == OreDictionary.WILDCARD_VALUE || meta == world.getBlockMetadata(x, y, z);
		}

		public ItemStack getReplacementBlock(World world, int x, int y, int z) {
			return replacement;
		}

		public void prePick(World world, int x, int y, int z) {}

		public List<ItemStack> getDrops(World world, Random rand, int x, int y, int z) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			for (int i = 0; i < possibleDrops.length; i++) {
				if (i >= chances.length || RANDOM.nextFloat() < chances[i]) drops.add(possibleDrops[i]);
			}
			return drops;
		}

		public void postPick(World world, int x, int y, int z) {}
	}
}
