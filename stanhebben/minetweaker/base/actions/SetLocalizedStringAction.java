package stanhebben.minetweaker.base.actions;

import cpw.mods.fml.common.registry.LanguageRegistry;
import stanhebben.minetweaker.api.IUndoableAction;

public class SetLocalizedStringAction implements IUndoableAction {
	private String key;
	private String lang;
	private String value;
	
	private String oldValue;
	
	public SetLocalizedStringAction(String key, String lang, String value) {
		this.key = key;
		this.lang = lang;
		this.value = value;
	}
	
	@Override
	public void apply() {
		oldValue = LanguageRegistry.instance().getStringLocalization(key, lang);
		LanguageRegistry.instance().addStringLocalization(key, lang, value);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		LanguageRegistry.instance().addStringLocalization(key, lang, oldValue);
	}

	public String describe() {
		return "Setting the " + lang + " translation for " + key + " to " + value;
	}

	public String describeUndo() {
		return "Restoring the " + lang + " translation for " + key + " to " + oldValue;
	}
}
