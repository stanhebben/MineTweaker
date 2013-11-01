/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.HarvesterAddHarvestableAction;

/**
 *
 * @author Stanneke
 */
public class HarvesterAddHarvestableFunction extends TweakerFunction {
	public static final HarvesterAddHarvestableFunction INSTANCE = new HarvesterAddHarvestableFunction();
	private static final Random RANDOM = new Random();
	
	private HarvesterAddHarvestableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		// Arguments:
		// - block
		// - possible drops (optional)
		// - chances of possible drops (optional)
		// - type (optional)
		if (arguments.length < 1 || arguments.length > 4) throw new TweakerExecuteException("harvester.addHarvestable requires 1-4 arguments");
		
		TweakerItem block =
				notNull(arguments[0], "harvestable cannot be null")
				.toItem("harvestable block must be an item");
		ItemStack[] possibleDrops;
		if (arguments.length < 2 || arguments[1] == null) {
			possibleDrops = new ItemStack[0];
		} else if (arguments[1].asArray() != null) {
			TweakerArray drops = arguments[1].asArray();
			possibleDrops = new ItemStack[drops.size()];
			
			for (int i = 0; i < possibleDrops.length; i++) {
				possibleDrops[i] =
						notNull(drops.get(i), "drops cannot be null")
						.toItemStack("drops must be items").get();
			}
		} else {
			possibleDrops = new ItemStack[] {
				arguments[1].toItemStack("drop must be an item stack or array of item stacks").get()
			};
		}
		
		float[] dropChances;
		if (arguments.length < 3 || arguments[2] == null) {
			dropChances = new float[0];
		} else if (arguments[2].asArray() != null) {
			TweakerArray chances = arguments[2].asArray();
			dropChances = new float[chances.size()];
			
			for (int i = 0; i < dropChances.length; i++) {
				dropChances[i] =
						notNull(chances.get(i), "drop chance cannot be null")
						.toFloat("drop chance must be a float").get();
			}
		} else {
			dropChances = new float[] {
				arguments[2].toFloat("drop chance must be a float or array of floats").get()
			};
		}
		
		HarvestType type;
		if (arguments.length < 4 || arguments[3] == null) {
			type = HarvestType.Normal;
		} else {
			String stringType = arguments[3].toBasicString();
			if (stringType.equals("normal")) {
				type = HarvestType.Normal;
			} else if (stringType.equals("column")) {
				type = HarvestType.Column;
			} else if (stringType.equals("leaveBottom")) {
				type = HarvestType.LeaveBottom;
			} else if (stringType.equals("tree")) {
				type = HarvestType.Tree;
			} else if (stringType.equals("treeFlipped")) {
				type = HarvestType.TreeFlipped;
			} else if (stringType.equals("treeLeaf")) {
				type = HarvestType.TreeLeaf;
			} else {
				throw new TweakerExecuteException("Unknown harvestable type: " + stringType);
			}
		}
		
		Tweaker.apply(new HarvesterAddHarvestableAction(block, new TweakerHarvestable(
				block.getItemId(), block.getItemSubId(),
				possibleDrops, dropChances,
				type)));
		return null;
	}

	@Override
	public String toString() {
		return "harvester.addHarvestable";
	}
	
	private static class TweakerHarvestable implements IFactoryHarvestable {
		private final int plantId;
		private final int meta;
		private final ItemStack[] possibleDrops;
		private final float[] chances;
		private final HarvestType type;
		
		public TweakerHarvestable(int plantId, int meta, ItemStack[] possibleDrops, float[] chances, HarvestType type) {
			this.plantId = plantId;
			this.meta = meta;
			this.possibleDrops = possibleDrops;
			this.chances = chances;
			this.type = type;
		}
		
		public int getPlantId() {
			return plantId;
		}

		public HarvestType getHarvestType() {
			return type;
		}

		public boolean breakBlock() {
			return true;
		}

		public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
			//return meta == OreDictionary.WILDCARD_VALUE || world.getBlockMetadata(x, y, z) == meta;
			return true;
		}

		public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			for (int i = 0; i < possibleDrops.length; i++) {
				if (i >= chances.length || RANDOM.nextFloat() < chances[i]) drops.add(possibleDrops[i]);
			}
			return drops;
		}

		public void preHarvest(World world, int x, int y, int z) {}

		public void postHarvest(World world, int x, int y, int z) {}
	}
}
