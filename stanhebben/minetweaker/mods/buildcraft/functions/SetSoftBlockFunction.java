/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import java.util.logging.Level;
import net.minecraft.block.Block;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.SetSoftBlockAction;

/**
 *
 * @author Stanneke
 */
public class SetSoftBlockFunction extends TweakerFunction {
	public static final SetSoftBlockFunction INSTANCE = new SetSoftBlockFunction();
	
	private SetSoftBlockFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("setSoftBlock requires at least one argument");
		}
		
		TweakerItem item =
				notNull(arguments[0], "the setSoftBlock block argument must not be null")
				.toItem("the setSoftBlock block argument must be a block");
		if (item.getItemId() >= Block.blocksList.length || Block.blocksList[item.getItemId()] == null) {
			throw new TweakerExecuteException("the setSoftBlock block argument must be a block");
		}
		
		boolean value = arguments.length == 1 ||
				notNull(arguments[1], "the setSoftBlock value argument must not be null")
				.toBool("the setSoftBlock value argument must be a bool value").get();
		
		if (item.isSubItem()) {
			Tweaker.log(Level.WARNING, "setSoftBlock will set all subblocks with id " + item.getItemId() + " to soft");
		}
		
		Tweaker.apply(new SetSoftBlockAction(item.getItemId(), value));
		
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.setSoftBlock";
	}
}
