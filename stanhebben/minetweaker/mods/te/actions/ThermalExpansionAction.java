package stanhebben.minetweaker.mods.te.actions;

import net.minecraft.nbt.NBTTagCompound;
import stanhebben.minetweaker.api.IUndoableAction;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ThermalExpansionAction implements IUndoableAction {
	private final String key;
	private final NBTTagCompound nbt;
	private final String description;
	
	public ThermalExpansionAction(String key, NBTTagCompound nbt, String description) {
		this.key = key;
		this.nbt = nbt;
		this.description = description;
	}
	
	@Override
	public void apply() {
		System.out.println("Sending message " + key + " to ThermalExpansion: " + nbt.toString());
		
		FMLInterModComms.sendMessage("ThermalExpansion", key, nbt);
	}
	
	@Override
	public boolean canUndo() {
		return false;
	}
	
	@Override
	public void undo() {
		// TODO: Currently no way to remove Thermal Expansion recipes once added.
	}
	
	@Override
	public String describe() {
		return "Adding a " + key + " for " + description;
	}
	
	@Override
	public String describeUndo() {
		return "Removing a " + key + " for " + description;
	}
}
