package stanhebben.minetweaker.script;

import java.io.IOException;
import java.util.HashMap;

import stanhebben.minetweaker.api.TweakerExecuteException;

public class ScriptEnvironmentVirtual implements ScriptEnvironment {
	private HashMap<String, String> contents;
	
	public ScriptEnvironmentVirtual(HashMap<String, String> contents) {
		this.contents = contents;
	}

	@Override
	public TweakerFile getFile(String file) {
		try {
			return new TweakerFile(this, file, contents.get(file));
		} catch (IOException ex) {
			throw new TweakerExecuteException(ex.getMessage());
		}
	}
}
