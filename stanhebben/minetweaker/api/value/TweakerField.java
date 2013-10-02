/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

import java.util.HashMap;

/**
 *
 * @author Stanneke
 */
public enum TweakerField {
	ABS,
	ADD,
	ADDCONTAINER,
	ADDRECIPE,
	ADDSHAPED,
	ADDSHAPELESS,
	AMOUNT,
	ANY,
	ASSEMBLYTABLE,
	BREAKUNDO,
	BYTEARRAY,
	CANCLEAR,
	CENTRIFUGE,
	CLEAR,
	COMPRESSOR,
	CONTAINS,
	COOLANTS,
	COOLING,
	CUTTING,
	DAMAGE,
	DAMAGED,
	DENSITY,
	DISPLAYNAME,
	EXTRACTOR,
	EXTRUDING,
	FLUID,
	FUEL,
	FUELS,
	GASEOUS,
	ID,
	INDEXOF,
	INTARRAY,
	ISINFINITE,
	ISNAN,
	ITEM,
	ITEMS,
	LENGTH,
	LIQUID,
	LUMINOSITY,
	MACERATOR,
	MATCHES,
	MATTERAMPLIFIER,
	MAXDAMAGE,
	META,
	METALFORMER,
	NAME,
	ONLY,
	POWERPERCYCLE,
	REFINERY,
	REMOVE,
	REMOVECONTAINER,
	REMOVERECIPE,
	REMOVESHAPED,
	REMOVESHAPELESS,
	ROLLING,
	SETDISPLAYNAME,
	SETENERGY,
	SETSOFTBLOCK,
	SPLIT,
	SUBS,
	SUBSTRING,
	TAG,
	TEMPERATURE,
	TOTALBURNINGTIME,
	_INVALID_;
	
	private static final HashMap<String, TweakerField> fieldForName;
	
	static {
		fieldForName = new HashMap<String, TweakerField>();
		fieldForName.put("abs", ABS);
		fieldForName.put("add", ADD);
		fieldForName.put("addContainer", ADDCONTAINER);
		fieldForName.put("addRecipe", ADDRECIPE);
		fieldForName.put("addShaped", ADDSHAPED);
		fieldForName.put("addShapeless", ADDSHAPELESS);
		fieldForName.put("amount", AMOUNT);
		fieldForName.put("any", ANY);
		fieldForName.put("assemblyTable", ASSEMBLYTABLE);
		fieldForName.put("byteArray", BYTEARRAY);
		fieldForName.put("canClear", CANCLEAR);
		fieldForName.put("centrifuge", CENTRIFUGE);
		fieldForName.put("clear", CLEAR);
		fieldForName.put("compressor", COMPRESSOR);
		fieldForName.put("contains", CONTAINS);
		fieldForName.put("coolants", COOLANTS);
		fieldForName.put("cooling", COOLING);
		fieldForName.put("damage", DAMAGE);
		fieldForName.put("damaged", DAMAGED);
		fieldForName.put("displayName", DISPLAYNAME);
		fieldForName.put("density", DENSITY);
		fieldForName.put("extractor", EXTRACTOR);
		fieldForName.put("fluid", FLUID);
		fieldForName.put("fuel", FUEL);
		fieldForName.put("fuels", FUELS);
		fieldForName.put("gaseous", GASEOUS);
		fieldForName.put("id", ID);
		fieldForName.put("indexOf", INDEXOF);
		fieldForName.put("intArray", INTARRAY);
		fieldForName.put("isInfinite", ISINFINITE);
		fieldForName.put("isNaN", ISNAN);
		fieldForName.put("item", ITEM);
		fieldForName.put("length", LENGTH);
		fieldForName.put("liquid", LIQUID);
		fieldForName.put("luminosity", LUMINOSITY);
		fieldForName.put("macerator", MACERATOR);
		fieldForName.put("matches", MATCHES);
		fieldForName.put("matterAmplifier", MATTERAMPLIFIER);
		fieldForName.put("maxDamage", MAXDAMAGE);
		fieldForName.put("meta", META);
		fieldForName.put("name", NAME);
		fieldForName.put("only", ONLY);
		fieldForName.put("powerPerCycle", POWERPERCYCLE);
		fieldForName.put("refinery", REFINERY);
		fieldForName.put("remove", REMOVE);
		fieldForName.put("removeContainer", REMOVECONTAINER);
		fieldForName.put("removeRecipe", REMOVERECIPE);
		fieldForName.put("removeShaped", REMOVESHAPED);
		fieldForName.put("removeShapeless", REMOVESHAPELESS);
		fieldForName.put("setDisplayName", SETDISPLAYNAME);
		fieldForName.put("setEnergy", SETENERGY);
		fieldForName.put("setSoftBlock", SETSOFTBLOCK);
		fieldForName.put("split", SPLIT);
		fieldForName.put("substring", SUBSTRING);
		fieldForName.put("subs", SUBS);
		fieldForName.put("tag", TAG);
		fieldForName.put("temperature", TEMPERATURE);
		fieldForName.put("totalBurningTime", TOTALBURNINGTIME);
	}
	
	public static TweakerField get(String value) {
		TweakerField result = fieldForName.get(value);
		if (result == null) return _INVALID_;
		return result;
	}
}
