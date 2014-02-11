package stanhebben.minetweaker.mods.te.actions;

import java.lang.reflect.Method;
import java.util.logging.Level;

import net.minecraft.nbt.NBTTagCompound;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

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
		FMLInterModComms.sendMessage("ThermalExpansion", key, nbt);
		// Do some hackery because IMCMessages need to be retrieved manually after they've
		// been sent out automatically the first time between init and post-init events.
		try {
			Class imcHandlerClass = Class.forName("thermalexpansion.util.IMCHandler");
			Method handleIMCMethod = imcHandlerClass.getMethod("handleIMC", IMCEvent.class);
			Object imcHandlerInstance = imcHandlerClass.getField("instance").get(null);
			handleIMCMethod.invoke(imcHandlerInstance, new CustumIMCEvent());
		} catch (Exception e) {
			Tweaker.log(Level.WARNING, "Error adding Thermal Expansion recipe.");
			System.out.println(e);
		}
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
	
	private class CustumIMCEvent extends IMCEvent {
		private ImmutableList<IMCMessage> messages;
		
		@Override
		public ImmutableList<IMCMessage> getMessages() {
			if (messages == null)
				messages = FMLInterModComms.fetchRuntimeMessages("ThermalExpansion");
			return messages;
		}
	}
}
