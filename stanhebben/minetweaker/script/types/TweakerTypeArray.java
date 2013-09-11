package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerArrayBytes;
import stanhebben.minetweaker.api.value.TweakerArrayInts;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeArray extends TweakerType {
	private TweakerType base;
	
	public TweakerTypeArray(TweakerType base) {
		this.base = base;
	}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to an array");
		
		TweakerArray input = original.toArray("value is not an array");
		if (base == TweakerTypeByte.INSTANCE) {
			byte[] result = new byte[input.size()];
			for (int i = 0; i < input.size(); i++) {
				result[i] = input.get(i).toByte("value cannot be converted to byte").get();
			}
			return new TweakerArrayBytes(result);
		} else if (base == TweakerTypeInt.INSTANCE) {
			int[] result = new int[input.size()];
			for (int i = 0; i < input.size(); i++) {
				result[i] = input.get(i).toInt("value cannot be converted to int").get();
			}
			return new TweakerArrayInts(result);
		} else {
			TweakerArrayValue result = new TweakerArrayValue();
			for (int i = 0; i < input.size(); i++) {
				result.addAssign(base.as(input.get(i)));
			}
			return result;
		}
	}
}
