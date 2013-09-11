/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.util;

import java.util.Iterator;

/**
 *
 * @author Stanneke
 */
public class EmptyIterator<T> implements Iterator<T> {
	public boolean hasNext() {
		return false;
	}

	public T next() {
		return null;
	}

	public void remove() {}
}
