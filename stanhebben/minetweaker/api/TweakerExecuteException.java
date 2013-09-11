package stanhebben.minetweaker.api;

import stanhebben.minetweaker.script.TweakerFile;

/**
 * TweakerExecuteExceptions are throws when something erronous happens inside
 * executed code. It is thrown in cases where no file or line is available,
 * and is converted to a TweakerException as soon as possible.
 * 
 * @author Stan Hebben
 */
public class TweakerExecuteException extends RuntimeException {
	public TweakerExecuteException(String explanation) {
		super(explanation);
	}
	
	public TweakerException handle(TweakerFile file, int line, int offset) {
		return new TweakerException(file, line, offset, getMessage());
	}
}
