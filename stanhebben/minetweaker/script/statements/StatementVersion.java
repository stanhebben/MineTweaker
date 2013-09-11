package stanhebben.minetweaker.script.statements;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.rewriter.TransformationException;
import stanhebben.minetweaker.script.TweakerFile;

public class StatementVersion extends TweakerStatement {
	private int version;
	
	public StatementVersion(TweakerFile file, int line, int offset, int version) {
		super(file, line, offset);
		
		this.version = version;
		if (version == 1) throw new TransformationException(); 
	}

	@Override
	public TweakerValue execute(TweakerNameSpace namespace)
			throws TweakerException {
		namespace.put("version", new TweakerInt(version));
		return null;
	}
}
