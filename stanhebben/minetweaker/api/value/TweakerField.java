package stanhebben.minetweaker.api.value;

import java.util.HashMap;

/**
 * Field enumerator with all field names used in minetweaker. Used extensively
 * when selecting members of built-in types.
 * 
 * @author Stan Hebben
 */
public enum TweakerField {
	ABS,
	ADD,
	ADDCONTAINER,
	ADDCRATING,
	ADDFUEL,
	ADDHARVESTABLE,
	ADDRAINITEM,
	ADDRECIPE,
	ADDRECIPEFUELCAN,
	ADDSHAPED,
	ADDSHAPELESS,
	ADDSMELTING,
	ADDSTOPITEM,
	ALLOYSMELTER,
	AMOUNT,
	ANY,
	ASSEMBLER,
	ASSEMBLYTABLE,
	BEES,
	BIOGASENGINE,
	BIOGENERATOR,
	BLACKLIST,
	BLASTFURNACE,
	BOTTLER,
	BREAKUNDO,
	BYTEARRAY,
	CANCLEAR,
	CANNER,
	CARPENTER,
	CENTRIFUGE,
	CHEMICAL,
	CLEAR,
	COMPRESSOR,
	CONTAINS,
	COOLANTS,
	COOLING,
	CUTTER,
	CUTTING,
	DAMAGE,
	DAMAGED,
	DENSEFLUIDGENERATOR,
	DENSITY,
	DIESELGENERATOR,
	DISPLAYNAME,
	DISTILLATIONTOWER,
	ELECTROLYZER,
	EXTRACTOR,
	EXTRUDING,
	FABRICATOR,
	FERMENTER,
	FLUID,
	FUEL,
	FUELS,
	FUSIONREACTOR,
	GASEOUS,
	GASTURBINE,
	GRINDER,
	HARVESTER,
	ID,
	IMPLOSIONCOMPRESSOR,
	INDEXOF,
	INTARRAY,
	ISINFINITE,
	ISNAN,
	ITEM,
	ITEMS,
	LATHE,
	LENGTH,
	LIQUID,
	LUMINOSITY,
	MACERATOR,
	MAGICGENERATOR,
	MATCHES,
	MATTERAMPLIFIER,
	MAXDAMAGE,
	META,
	METALFORMER,
	MOISTENER,
	NAME,
	ONLY,
	OREWASHINGPLANT,
	PEATFIREDENGINE,
	PLASMAGENERATOR,
	PLATEBENDER,
	POWERPERCYCLE,
	PRINTGENES,
	RAINMAKER,
	REFINERY,
	REMOVE,
	REMOVECONTAINER,
	REMOVEFUEL,
	REMOVEITEM,
	REMOVERECIPE,
	REMOVESHAPED,
	REMOVESHAPELESS,
	ROLLING,
	SAWMILL,
	SETADMINS,
	SETDISPLAYNAME,
	SETENERGY,
	SETSOFTBLOCK,
	SPLIT,
	SQUEEZER,
	STILL,
	SUBS,
	SUBSTRING,
	TAG,
	TEMPERATURE,
	THERMALCENTRIFUGE,
	THERMALGENERATOR,
	TOTALBURNINGTIME,
	VACUUMFREEZER,
	WIREMILL,
	WITHDAMAGE,
	_INVALID_;
	
	private static final HashMap<String, TweakerField> fieldForName;
	
	static {
		fieldForName = new HashMap<String, TweakerField>();
		fieldForName.put("abs", ABS);
		fieldForName.put("add", ADD);
		fieldForName.put("addContainer", ADDCONTAINER);
		fieldForName.put("addCrating", ADDCRATING);
		fieldForName.put("addFuel", ADDFUEL);
		fieldForName.put("addHarvestable", ADDHARVESTABLE);
		fieldForName.put("addRainItem", ADDRAINITEM);
		fieldForName.put("addRecipe", ADDRECIPE);
		fieldForName.put("addRecipeFuelCan", ADDRECIPEFUELCAN);
		fieldForName.put("addShaped", ADDSHAPED);
		fieldForName.put("addShapeless", ADDSHAPELESS);
		fieldForName.put("addSmelting", ADDSMELTING);
		fieldForName.put("addStopItem", ADDSTOPITEM);
		fieldForName.put("alloySmelter", ALLOYSMELTER);
		fieldForName.put("amount", AMOUNT);
		fieldForName.put("any", ANY);
		fieldForName.put("assembler", ASSEMBLER);
		fieldForName.put("assemblyTable", ASSEMBLYTABLE);
		fieldForName.put("bees", BEES);
		fieldForName.put("biogasEngine", BIOGASENGINE);
		fieldForName.put("bioGenerator", BIOGENERATOR);
		fieldForName.put("blacklist", BLACKLIST);
		fieldForName.put("blastFurnace", BLASTFURNACE);
		fieldForName.put("bottler", BOTTLER);
		fieldForName.put("byteArray", BYTEARRAY);
		fieldForName.put("canClear", CANCLEAR);
		fieldForName.put("canner", CANNER);
		fieldForName.put("carpenter", CARPENTER);
		fieldForName.put("centrifuge", CENTRIFUGE);
		fieldForName.put("chemical", CHEMICAL);
		fieldForName.put("clear", CLEAR);
		fieldForName.put("compressor", COMPRESSOR);
		fieldForName.put("contains", CONTAINS);
		fieldForName.put("coolants", COOLANTS);
		fieldForName.put("cooling", COOLING);
		fieldForName.put("cutter", CUTTER);
		fieldForName.put("damage", DAMAGE);
		fieldForName.put("damaged", DAMAGED);
		fieldForName.put("denseFluidGenerator", DENSEFLUIDGENERATOR);
		fieldForName.put("dieselGenerator", DIESELGENERATOR);
		fieldForName.put("displayName", DISPLAYNAME);
		fieldForName.put("distillationTower", DISTILLATIONTOWER);
		fieldForName.put("density", DENSITY);
		fieldForName.put("electrolyzer", ELECTROLYZER);
		fieldForName.put("extractor", EXTRACTOR);
		fieldForName.put("fabricator", FABRICATOR);
		fieldForName.put("fermenter", FERMENTER);
		fieldForName.put("fluid", FLUID);
		fieldForName.put("fuel", FUEL);
		fieldForName.put("fuels", FUELS);
		fieldForName.put("fusionReactor", FUSIONREACTOR);
		fieldForName.put("gaseous", GASEOUS);
		fieldForName.put("gasTurbine", GASTURBINE);
		fieldForName.put("grinder", GRINDER);
		fieldForName.put("harvester", HARVESTER);
		fieldForName.put("id", ID);
		fieldForName.put("indexOf", INDEXOF);
		fieldForName.put("intArray", INTARRAY);
		fieldForName.put("implosionCompressor", IMPLOSIONCOMPRESSOR);
		fieldForName.put("isInfinite", ISINFINITE);
		fieldForName.put("isNaN", ISNAN);
		fieldForName.put("item", ITEM);
		fieldForName.put("lathe", LATHE);
		fieldForName.put("length", LENGTH);
		fieldForName.put("liquid", LIQUID);
		fieldForName.put("luminosity", LUMINOSITY);
		fieldForName.put("macerator", MACERATOR);
		fieldForName.put("magicGenerator", MAGICGENERATOR);
		fieldForName.put("matches", MATCHES);
		fieldForName.put("matterAmplifier", MATTERAMPLIFIER);
		fieldForName.put("maxDamage", MAXDAMAGE);
		fieldForName.put("meta", META);
		fieldForName.put("moistener", MOISTENER);
		fieldForName.put("name", NAME);
		fieldForName.put("only", ONLY);
		fieldForName.put("oreWashingPlant", OREWASHINGPLANT);
		fieldForName.put("peatFiredEngine", PEATFIREDENGINE);
		fieldForName.put("plasmaGenerator", PLASMAGENERATOR);
		fieldForName.put("plateBender", PLATEBENDER);
		fieldForName.put("printGenes", PRINTGENES);
		fieldForName.put("powerPerCycle", POWERPERCYCLE);
		fieldForName.put("rainmaker", RAINMAKER);
		fieldForName.put("refinery", REFINERY);
		fieldForName.put("remove", REMOVE);
		fieldForName.put("removeContainer", REMOVECONTAINER);
		fieldForName.put("removeFuel", REMOVEFUEL);
		fieldForName.put("removeItem", REMOVEITEM);
		fieldForName.put("removeRecipe", REMOVERECIPE);
		fieldForName.put("removeShaped", REMOVESHAPED);
		fieldForName.put("removeShapeless", REMOVESHAPELESS);
		fieldForName.put("setAdmins", SETADMINS);
		fieldForName.put("setDisplayName", SETDISPLAYNAME);
		fieldForName.put("setEnergy", SETENERGY);
		fieldForName.put("setSoftBlock", SETSOFTBLOCK);
		fieldForName.put("split", SPLIT);
		fieldForName.put("squeezer", SQUEEZER);
		fieldForName.put("still", STILL);
		fieldForName.put("substring", SUBSTRING);
		fieldForName.put("subs", SUBS);
		fieldForName.put("sawmill", SAWMILL);
		fieldForName.put("tag", TAG);
		fieldForName.put("temperature", TEMPERATURE);
		fieldForName.put("thermalCentrifuge", THERMALCENTRIFUGE);
		fieldForName.put("thermalGenerator", THERMALGENERATOR);
		fieldForName.put("totalBurningTime", TOTALBURNINGTIME);
		fieldForName.put("vacuumFreezer", VACUUMFREEZER);
		fieldForName.put("wiremill", WIREMILL);
		fieldForName.put("withDamage", WITHDAMAGE);
	}
	
	public static TweakerField get(String value) {
		TweakerField result = fieldForName.get(value);
		if (result == null) return _INVALID_;
		return result;
	}
}
