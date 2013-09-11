/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.actions;

import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class UndoBreakerAction implements IUndoableAction {
	public void apply() {
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
	}

	public String describe() {
		return "Breaking undo";
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
