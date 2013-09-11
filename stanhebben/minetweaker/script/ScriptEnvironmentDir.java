package stanhebben.minetweaker.script;

import java.io.File;
import java.io.IOException;

import stanhebben.minetweaker.api.TweakerExecuteException;

public class ScriptEnvironmentDir implements ScriptEnvironment {
	private File dir;
	
	public ScriptEnvironmentDir(File dir) {
		this.dir = dir;
	}
 
	@Override
	public TweakerFile getFile(String file) {
		try {
			String name = file;
			if (file.startsWith("/")) file = file.substring(1);
			return new TweakerFile(this, name, new File(dir, file));
		} catch (IOException ex) {
			throw new TweakerExecuteException(ex.getMessage());
		}
	}
}
