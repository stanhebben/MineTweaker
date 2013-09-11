package stanhebben.minetweaker.util;

import java.util.ArrayList;

import stanhebben.minetweaker.api.IUndoableAction;

public class DefaultUndoStack implements IUndoStack {
	private ArrayList<IUndoableAction> contents;
	private boolean disabled;
	
	public DefaultUndoStack() {
		contents = new ArrayList<IUndoableAction>();
	}
	
	public void disable() {
		if (disabled) return;
		disabled = true;
		
		for (int i = contents.size() - 1; i >= 0; i--) {
			contents.get(i).undo();
		}
	}
	
	public void enable() {
		if (!disabled) return;
		disabled = false;
		
		for (int i = 0; i < contents.size(); i++) {
			contents.get(i).apply();
		}
	}
	
	public IUndoableAction pop() {
		return contents.remove(contents.size() - 1);
	}
	
	public int size() {
		return contents.size();
	}

	@Override
	public void push(IUndoableAction action) {
		contents.add(action);
	}
}
