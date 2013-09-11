package stanhebben.minetweaker.util;

import stanhebben.minetweaker.api.IUndoableAction;

public interface IUndoStack {
	public void push(IUndoableAction action);
}
