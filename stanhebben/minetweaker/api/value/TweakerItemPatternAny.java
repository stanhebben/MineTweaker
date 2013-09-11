package stanhebben.minetweaker.api.value;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.MineTweakerRegistry;

public class TweakerItemPatternAny extends TweakerItemPattern {
	public static final TweakerItemPatternAny INSTANCE = new TweakerItemPatternAny();
	
	private TweakerItemPatternAny() {}
	
	@Override
	public boolean matches(TweakerItem item) {
		return true;
	}
	
	@Override
	public boolean matches(ItemStack item) {
		return true;
	}
	
	@Override
	public boolean matches(Object object) {
		return true;
	}

	@Override
	public boolean matches(int id) {
		return true;
	}

	@Override
	public boolean matches(int id, int meta) {
		return true;
	}
	
	@Override
	public String toPatternString() {
		return "<*>";
	}

	@Override
	public String toString() {
		return "<*>";
	}

	@Override
	public Iterator<TweakerItem> getMatches() {
		return MineTweakerRegistry.INSTANCE.getAllItems().iterator();
	}

	@Override
	public void addListener(IPatternListener listener) {
		// no items are assumed to be ever added
	}

	@Override
	public void removeListener(IPatternListener listener) {
		// no items are assumed to be ever added
	}
}
