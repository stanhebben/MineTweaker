//#fileifndef OLDIC2
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.ic2.actions;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import java.util.logging.Level;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class MachineAddRecipeAction implements IUndoableAction {
	private final IMachineRecipeManager machine;
	private final NBTTagCompound nbt;
	private final String machineName;
	private final IRecipeInput input;
	private final ItemStack[] output;
	
	public MachineAddRecipeAction(IMachineRecipeManager machine, String machineName, IRecipeInput input, NBTTagCompound nbt, ItemStack... output) {
		this.machine = machine;
		this.machineName = machineName;
		this.input = input;
		this.output = output;
		this.nbt = nbt;
	}
	
	public void apply() {
		machine.addRecipe(input, nbt, output);
	}

	public boolean canUndo() {
		return true; // TODO: test this
	}

	public void undo() {
		// not sure if this evil science works
		machine.getRecipes().remove(input);
	}

	public String describe() {
		return "Adding a " + machineName + " recipe for " + output[0].getDisplayName();
	}

	public String describeUndo() {
		return "Removing a " + machineName + " recipe for " + output[0].getDisplayName();
	}
}
