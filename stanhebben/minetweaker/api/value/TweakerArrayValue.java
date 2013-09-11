package stanhebben.minetweaker.api.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Generic array implementation. Can contain any kind of TweakerValue.
 * 
 * @author Stan Hebben
 */
public class TweakerArrayValue extends TweakerArray {
	private ArrayList<TweakerValue> contents;
	
	public TweakerArrayValue() {
		contents = new ArrayList<TweakerValue>();
	}
	
	public TweakerArrayValue(List<TweakerValue> values) {
		contents = new ArrayList<TweakerValue>();
		contents.addAll(values);
	}
	
	@Override
	public int size() {
		return contents.size();
	}
	
	@Override
	public TweakerValue get(int index) {
		return contents.get(index);
	}
	
	@Override
	public TweakerValue remove(int index) {
		return contents.remove(index);
	}
	
	@Override
	public TweakerValue addAssign(TweakerValue value) {
		contents.add(value);
		return this;
	}
	
	@Override
	public TweakerValue subAssign(TweakerValue value) {
		contents.remove(value);
		return this;
	}
	
	@Override
	public Iterator<TweakerValue> iterator() {
		return contents.iterator();
	}
}
