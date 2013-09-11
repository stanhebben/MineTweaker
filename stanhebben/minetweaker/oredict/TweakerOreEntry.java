/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.oredict;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class TweakerOreEntry {
	private List<OreEntryListener> listeners = null;
	private String name;
	
	public TweakerOreEntry(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addItem(TweakerItem item) {
		OreDictionary.registerOre(name, item.make(1));
		if (listeners != null) {
			for (OreEntryListener listener : listeners) {
				listener.itemAdded(this, item);
			}
		}
	}
	
	public void addWildcard(int id) {
		OreDictionary.registerOre(name, new ItemStack(id, 1, OreDictionary.WILDCARD_VALUE));
		if (listeners != null) {
			for (OreEntryListener listener : listeners) {
				listener.itemWildcardAdded(this, id);
			}
		}
	}
	
	public void removeItem(TweakerItem item) {
		OreDictionary.getOres(name).remove(item.make(1));
		if (listeners != null) {
			for (OreEntryListener listener : listeners) {
				listener.itemRemoved(this, item);
			}
		}
	}
	
	public void removeWildcard(int id) {
		OreDictionary.registerOre(name, new ItemStack(id, 1, OreDictionary.WILDCARD_VALUE));
		if (listeners != null) {
			for (OreEntryListener listener : listeners) {
				listener.itemWildcardRemoved(this, id);
			}
		}
	}
	
	public List<TweakerItem> getItems() {
		return new WrappingList(OreDictionary.getOres(name));
	}
	
	public void addListener(OreEntryListener listener) {
		if (listeners == null) listeners = new ArrayList<OreEntryListener>();
		listeners.add(listener);
	}
	
	public void removeListener(OreEntryListener listener) {
		listeners.remove(listener);
		if (listeners.isEmpty()) listeners = null;
	}
	
	private class WrappingList implements List<TweakerItem> {
		private List<ItemStack> original;
		
		private WrappingList(List<ItemStack> original) {
			this.original = original;
		}
		
		public int size() {
			return original.size();
		}

		public boolean isEmpty() {
			return original.isEmpty();
		}

		public boolean contains(Object o) {
			if (!(o instanceof TweakerItem)) return false;
			return original.contains(((TweakerItem)o).make(1));
		}

		public Iterator<TweakerItem> iterator() {
			return new Iterator<TweakerItem>() {
				private Iterator<ItemStack> baseIterator = original.iterator();
				
				public boolean hasNext() {
					return baseIterator.hasNext();
				}

				public TweakerItem next() {
					return TweakerItem.get(baseIterator.next());
				}

				public void remove() {
					baseIterator.remove();
				}
			};
		}

		public Object[] toArray() {
			TweakerItem[] result = new TweakerItem[original.size()];
			int i = 0;
			Iterator<ItemStack> iter = original.iterator();
			while (iter.hasNext()) {
				result[i++] = TweakerItem.get(iter.next());
			}
			return result;
		}

		public <T> T[] toArray(T[] a) {
			TweakerItem[] result = new TweakerItem[original.size()];
			int i = 0;
			Iterator<ItemStack> iter = original.iterator();
			while (iter.hasNext()) {
				result[i++] = TweakerItem.get(iter.next());
			}
			return (T[]) result;
		}

		public boolean add(TweakerItem e) {
			return original.add(e.make(1));
		}

		public boolean remove(Object o) {
			if (!(o instanceof TweakerItem)) return false;
			return original.remove(((TweakerItem)o).make(1));
		}

		public boolean containsAll(Collection<?> c) {
			for (Object o : c) {
				if (!(contains(o))) return false;
			}
			return true;
		}

		public boolean addAll(Collection<? extends TweakerItem> c) {
			boolean result = false;
			for (TweakerItem item : c) {
				result |= original.add(item.make(1));
			}
			return result;
		}

		public boolean addAll(int index, Collection<? extends TweakerItem> c) {
			for (TweakerItem item : c) {
				original.add(index++, item.make(1));
			}
			return !c.isEmpty();
		}

		public boolean removeAll(Collection<?> c) {
			boolean result = false;
			for (Object o : c) {
				result |= remove(o);
			}
			return result;
		}

		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		public void clear() {
			original.clear();
		}

		public TweakerItem get(int index) {
			return TweakerItem.get(original.get(index));
		}

		public TweakerItem set(int index, TweakerItem element) {
			return TweakerItem.get(original.set(index, element.make(1)));
		}

		public void add(int index, TweakerItem element) {
			original.add(index, element.make(1));
		}

		public TweakerItem remove(int index) {
			return TweakerItem.get(original.remove(index));
		}

		public int indexOf(Object o) {
			if (!(o instanceof TweakerItem)) return -1;
			return original.indexOf(((TweakerItem)o).make(1));
		}

		public int lastIndexOf(Object o) {
			if (!(o instanceof TweakerItem)) return -1;
			return original.lastIndexOf(((TweakerItem)o).make(1));
		}

		public ListIterator<TweakerItem> listIterator() {
			return new ListIterator<TweakerItem>() {
				ListIterator<ItemStack> originalIter = original.listIterator();
				
				public boolean hasNext() {
					return originalIter.hasNext();
				}

				public TweakerItem next() {
					return TweakerItem.get(originalIter.next());
				}

				public boolean hasPrevious() {
					return originalIter.hasPrevious();
				}

				public TweakerItem previous() {
					return TweakerItem.get(originalIter.previous());
				}

				public int nextIndex() {
					return originalIter.nextIndex();
				}

				public int previousIndex() {
					return originalIter.previousIndex();
				}

				public void remove() {
					originalIter.remove();
				}

				public void set(TweakerItem e) {
					originalIter.set(e.make(1));
				}

				public void add(TweakerItem e) {
					originalIter.add(e.make(1));
				}
			};
		}

		public ListIterator<TweakerItem> listIterator(final int index) {
			return new ListIterator<TweakerItem>() {
				ListIterator<ItemStack> originalIter = original.listIterator(index);
				
				public boolean hasNext() {
					return originalIter.hasNext();
				}

				public TweakerItem next() {
					return TweakerItem.get(originalIter.next());
				}

				public boolean hasPrevious() {
					return originalIter.hasPrevious();
				}

				public TweakerItem previous() {
					return TweakerItem.get(originalIter.previous());
				}

				public int nextIndex() {
					return originalIter.nextIndex();
				}

				public int previousIndex() {
					return originalIter.previousIndex();
				}

				public void remove() {
					originalIter.remove();
				}

				public void set(TweakerItem e) {
					originalIter.set(e.make(1));
				}

				public void add(TweakerItem e) {
					originalIter.add(e.make(1));
				}
			};
		}

		public List<TweakerItem> subList(int fromIndex, int toIndex) {
			return new WrappingList(original.subList(fromIndex, toIndex));
		}
	}
}
