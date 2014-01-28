/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.transformer;

import java.util.HashMap;
import net.minecraft.launchwrapper.IClassTransformer;

/**
 *
 * @author Stanneke
 */
public class MineTweakerASM implements IClassTransformer {
	private static final HashMap<String, IMineTweakerTransformer> transformers;
	public static boolean debugMode = true;
	
	public static final String METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_N = "applyArmorCalculations";
	public static final String METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_O = "b";
	public static final String METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_SIG_N = "(Lnet/minecraft/src/DamageSource;F)F";
	public static final String METHOD_ENTITYLIVINGBASE_APPLYARMORCALCULATIONS_SIG_O = "(Lnb;F)F";
	
	public static final String CLASS_ENTITYLIVINGBASE_N = "net/minecraft/entity/EntityLivingBase";
	public static final String CLASS_ENTITYLIVINGBASE_O = "of";
	public static final String CLASS_DAMAGESOURCE_N = "net/minecraft/util/DamageSource";
	public static final String CLASS_DAMAGESOURCE_O = "nb";
	public static final String CLASS_ENTITYPLAYER_N = "net/minecraft/entity/player/EntityPlayer";
	public static final String CLASS_ENTITYPLAYER_O = "uf";
	
	static {
		transformers = new HashMap<String, IMineTweakerTransformer>();
		transformers.put(CLASS_ENTITYLIVINGBASE_N, new EntityLivingBaseTransformer(false));
		transformers.put(CLASS_ENTITYLIVINGBASE_O, new EntityLivingBaseTransformer(true));
	}
	
	@Override
	public byte[] transform(String name, String newName, byte[] bytecode) {
		if (transformers.containsKey(name)) {
			//System.out.println("MineTweaker Transforming " + name + " (" + newName + ")");
			return transformers.get(name).transform(bytecode);
		} else {
			return bytecode;
		}
	}
}
