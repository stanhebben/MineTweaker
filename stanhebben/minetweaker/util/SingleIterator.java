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
public class SingleIterator<T> implements Iterator<T> {
	private T item;
	private boolean has;
	
	public SingleIterator(T item) {
		this.item = item;
		has = true;
	}
	
	public boolean hasNext() {
		return has;
	}

	public T next() {
		has = false;
		return item;
	}

	public void remove() {}
}
