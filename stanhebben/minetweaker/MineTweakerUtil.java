package stanhebben.minetweaker;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.server.MinecraftServer;
//#ifdef MC152
//+import net.minecraftforge.liquids.LiquidContainerData;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//#else
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
//#endif
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.value.TweakerItem;

public class MineTweakerUtil {
	private static Field oreRecipeWidth = null;
	private static Field anvilFile = null;
	
	//#ifdef MC152
	//+static Map<List, LiquidContainerData> mapFilledItemFromLiquid = null;
	//+static Map<List, LiquidContainerData> mapLiquidFromFilledItem = null;
	//+static Set<List> setContainerValidation = null;
	//#else
	static Map<List, FluidContainerData> containerFluidMap = null; // id + damage + fluid => filled
	static Map<List, FluidContainerData> filledContainerMap = null; // id + damage => empty
	static Set<List> emptyContainers = null;
	//#endif
	
	static {
		oreRecipeWidth = getPrivateField(ShapedOreRecipe.class, "width");
		if (oreRecipeWidth == null) {
			Tweaker.log(Level.SEVERE, "Could not get ShapedOreRecipe width field. Cannot use ore recipe patterns.");
		}
		
		anvilFile = getPrivateField(MinecraftServer.class, "field_71308_o", "anvilFile");
		if (anvilFile == null) {
			Tweaker.log(Level.SEVERE, "Could not get MinecraftServer anvilFile field. Cannot use server scripts.");
		}
		
		//#ifdef MC152
		//+mapFilledItemFromLiquid = getPrivateStaticObject(LiquidContainerRegistry.class, "mapFilledItemFromLiquid");
		//+if (mapFilledItemFromLiquid == null) {
			//+Tweaker.log(Level.SEVERE, "Could not get LiquidContainerRegistry mapFilledItemFromLiquid field. Cannot remove fluid containers.");
		//+}
		
		//+mapLiquidFromFilledItem = getPrivateStaticObject(LiquidContainerRegistry.class, "mapLiquidFromFilledItem");
		//+if (mapLiquidFromFilledItem == null) {
			//+Tweaker.log(Level.SEVERE, "Could not get LiquidContainerRegistry mapLiquidFromFilledItem field. Cannot remove fluid containers.");
		//+}
		
		//+setContainerValidation = getPrivateStaticObject(LiquidContainerRegistry.class, "setContainerValidation");
		//+if (setContainerValidation == null) {
			//+Tweaker.log(Level.SEVERE, "Could not get LiquidContainerRegistry setContainerValidation field. Cannot remove fluid containers.");
		//+}
		//#else
		containerFluidMap = getPrivateStaticObject(FluidContainerRegistry.class, "containerFluidMap");
		if (containerFluidMap == null) {
			Tweaker.log(Level.SEVERE, "Could not get FluidContainerRegistry containerFluidMap field. Cannot remove fluid containers.");
		}
		
		filledContainerMap = getPrivateStaticObject(FluidContainerRegistry.class, "filledContainerMap");
		if (filledContainerMap == null) {
			Tweaker.log(Level.SEVERE, "Could not get FluidContainerRegistry filledContainerMap field. Cannot remove fluid containers.");
		}
		
		emptyContainers = getPrivateStaticObject(FluidContainerRegistry.class, "emptyContainers");
		if (emptyContainers == null) {
			Tweaker.log(Level.SEVERE, "Could not get FluidContainerRegistry emptyContainers field. Cannot remove fluid containers.");
		}
		//#endif
	}

	private MineTweakerUtil() {}
	
	public static Field getPrivateField(Class cls, String... fieldName) {
		for (String field : fieldName) {
			try {
				Field result = cls.getDeclaredField(field);
				result.setAccessible(true);
				return result;
			} catch (NoSuchFieldException ex) {
				
			} catch (SecurityException ex) {
				
			}
		}
		return null;
	}
	
	public static <T> T getPrivateStaticObject(Class cls, String... fieldName) {
		for (String field : fieldName) {
			try {
				Field result = cls.getDeclaredField(field);
				result.setAccessible(true);
				return (T) result.get(null);
			} catch (NoSuchFieldException ex) {
				
			} catch (SecurityException ex) {
				
			} catch (IllegalAccessException ex) {
				
			}
		}
		return null;
	}
	
	public static String getOreDictionary(List item) {
		return OreDictionary.getOreName(OreDictionary.getOreID((ItemStack) item.get(0)));
	}
	
	public static boolean hasShapedOreRecipeWidth() {
		return oreRecipeWidth != null;
	}
	
	public static int getShapedOreRecipeWidth(ShapedOreRecipe recipe) {
		if (oreRecipeWidth == null) return 1;
		try {
			return oreRecipeWidth.getInt(recipe);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public static String getItemString(ItemStack stack) {
		if (stack == null) {
			return "null";
		} else {
			return stack.getItemDamage() == OreDictionary.WILDCARD_VALUE
					? Integer.toString(stack.itemID)
					: stack.itemID + ":" + stack.getItemDamage();
		}
	}
	
	public static String getItemStackString(ItemStack stack) {
		String base = getItemString(stack);
		if (stack.stackSize != 1) {
			base += " * " + stack.stackSize;
		}
		return base;
	}
	
	public static String getRecipeString(IRecipe recipe) {
		if (recipe instanceof ShapedRecipes) {
			ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
			
			StringBuilder result = new StringBuilder();
			result.append("Shaped ").append(shapedRecipe.recipeWidth).append('x').append(shapedRecipe.recipeHeight).append(": ");
			result.append(getItemStackString(recipe.getRecipeOutput()));
			result.append(" = [[");
			for (int i = 0; i < shapedRecipe.recipeHeight; i++) {
				if (i > 0) result.append("], [");
				for (int j = 0; j < shapedRecipe.recipeWidth; j++) {
					if (j > 0) result.append(", ");
					result.append(getItemString(shapedRecipe.recipeItems[i * shapedRecipe.recipeWidth + j]));
				}
			}
			result.append("]]");
			return result.toString();
		} else if (recipe instanceof ShapelessRecipes) {
			ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
			
			StringBuilder result = new StringBuilder();
			result.append("Shapeless: ");
			result.append(getItemStackString(recipe.getRecipeOutput()));
			result.append(" = [");
			for (int i = 0; i < recipe.getRecipeSize(); i++) {
				if (i > 0) result.append(", ");
				result.append(getItemString((ItemStack) shapelessRecipe.recipeItems.get(i)));
			}
			result.append("]");
			return result.toString();
		} else if (recipe instanceof ShapedOreRecipe) {
			ShapedOreRecipe shapedRecipe = (ShapedOreRecipe) recipe;
			
			int recipeWidth = getShapedOreRecipeWidth(shapedRecipe);
			int recipeHeight = shapedRecipe.getRecipeSize() / recipeWidth;

			StringBuilder result = new StringBuilder();
			result.append("Shaped Ore ").append(recipeWidth).append("x").append(recipeHeight).append(": ");
			result.append(getItemStackString(recipe.getRecipeOutput()));
			result.append(" = [[");
			for (int i = 0; i < recipeHeight; i++) {
				if (i > 0) result.append("], [");
				for (int j = 0; j < recipeWidth; j++) {
					if (j > 0) result.append(", ");
					
					Object input = shapedRecipe.getInput()[i * recipeWidth + j];
					if (input == null) {
						result.append("null");
					} else if (input instanceof ItemStack) {
						result.append(getItemString((ItemStack) input));
					} else if (input instanceof List) {
						result.append("oreDict.").append(getOreDictionary((List) input));
					} else {
						result.append(input.toString());
					}
				}
			}
			return result.toString();
		} else if (recipe instanceof ShapelessOreRecipe) {
			ShapelessOreRecipe shapelessRecipe = (ShapelessOreRecipe) recipe;
			
			StringBuilder result = new StringBuilder();
			result.append("Shapeless Ore: ");
			result.append(getItemStackString(shapelessRecipe.getRecipeOutput()));
			result.append(" = [");
			
			for (int i = 0; i < shapelessRecipe.getRecipeSize(); i++) {
				if (i > 0) result.append(", ");
				
				Object input = shapelessRecipe.getInput().get(i);
				if (input instanceof ItemStack) {
					result.append(getItemString((ItemStack) input));
				} else if (input instanceof List) {
					result.append("oreDict.").append(getOreDictionary((List) input));
				} else {
					result.append(input.toString());
				}
			}
			
			return result.toString();
		} else {
			return "(" + recipe.getClass() + ") " + getItemStackString(recipe.getRecipeOutput());
		}
	}
	
	public static File getAnvilFile(MinecraftServer server) {
		if (anvilFile == null) return null;
		try {
			return (File) anvilFile.get(server);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static File getWorldDirectory(MinecraftServer server) {
		if (server.isDedicatedServer()) {
			return server.getFile("world");
		} else {
			File worldsDir = getAnvilFile(server);
		    if (worldsDir == null) return null;
		    
		    return new File(worldsDir, server.getFolderName());
		}
	}
	
	public static void getSubBlocks(int id, List<ItemStack> output) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isDedicatedServer()) {
			// why, oh why, did they not implement getSubBlocks on dedicated servers. fuck you Forge!
			HashSet<String> found = new HashSet<String>();
			for (int i = 0; true; i++) {
				ItemStack value = new ItemStack(id, 1, i);
				try {
					String name = Block.blocksList[id].getUnlocalizedName();
					if (name != null && !found.contains(name)) {
						found.add(name);
						output.add(value);
						continue;
					}
				} catch (Exception ex) {}
				break;
			}
		} else {
			Block.blocksList[id].getSubBlocks(id, null, output);
		}
	}
	
	public static void getSubItems(int id, List<ItemStack> output) {
		if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isDedicatedServer()) {
			// why, oh why, did they not implement getSubItems on dedicated servers. fuck you Forge!
			HashSet<String> found = new HashSet<String>();
			for (int i = 0; true; i++) {
				ItemStack value = new ItemStack(id, 1, i);
				try {
					String name = Item.itemsList[id].getUnlocalizedName();
					if (name != null && !found.contains(name)) {
						found.add(name);
						output.add(value);
						continue;
					}
				} catch (Exception ex) {}
				break;
			}
		} else {
			Item.itemsList[id].getSubItems(id, null, output);
		}
	}
	
	public static boolean canRemoveContainer() {
		//#ifdef MC152
		//+return setContainerValidation != null && mapFilledItemFromLiquid != null && mapLiquidFromFilledItem != null;
		//#else
		return emptyContainers != null && containerFluidMap != null && filledContainerMap != null;
		//#endif
	}
	
	public static boolean hasEmptyContainer(TweakerItem item) {
		//#ifdef MC152
		//+return setContainerValidation.contains(Arrays.asList(item.getItemId(), item.getItemSubId()));
		//#else
		return emptyContainers.contains(Arrays.asList(item.getItemId(), item.getItemSubId()));
		//#endif
	}
	
	//#ifdef MC152
	//+public static boolean removeEmptyContainer(ItemStack item) {
		//+return setContainerValidation.remove(Arrays.asList(item.itemID, item.getItemDamage()));
	//+}
	//#else
	public static boolean removeEmptyContainer(TweakerItem item) {
		return emptyContainers.remove(Arrays.asList(item.getItemId(), item.getItemSubId()));
	}
	//#endif

	//#ifdef MC152
	//+public static boolean removeContainer(TweakerItem filled) {
		//+List filledId = Arrays.asList(filled.getItemId(), filled.getItemSubId());
		//+if (mapLiquidFromFilledItem.containsKey(filledId)) {
			//+LiquidContainerData data = mapLiquidFromFilledItem.get(filledId);
			//+mapLiquidFromFilledItem.remove(filledId);
			//+mapFilledItemFromLiquid.remove(Arrays.asList(data.stillLiquid.itemID, data.stillLiquid.itemMeta));
			//+return true;
		//+} else {
			//+return false;
		//+}
	//+}
	//#else
	public static boolean removeContainer(TweakerItem filled) {
		List filledId = Arrays.asList(filled.getItemId(), filled.getItemSubId());
		if (filledContainerMap.containsKey(filledId)) {
			FluidContainerData data = filledContainerMap.get(filledId);
			filledContainerMap.remove(filledId);
			containerFluidMap.remove(Arrays.asList(data.emptyContainer.itemID, data.emptyContainer.getItemDamage(), data.fluid));
			return true;
		} else {
			return false;
		}
	}
	//#endif
	
	//#ifdef MC152
	//+public static LiquidContainerData getContainerData(TweakerItem filled) {
		//+return mapLiquidFromFilledItem.get(Arrays.asList(filled.getItemId(), filled.getItemSubId()));
	//+}
	//#else
	public static FluidContainerData getContainerData(TweakerItem filled) {
		return filledContainerMap.get(Arrays.asList(filled.getItemId(), filled.getItemSubId()));
	}
	//#endif
}
