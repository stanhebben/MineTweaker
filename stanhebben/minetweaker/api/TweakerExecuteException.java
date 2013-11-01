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
	
	/**
	 * Called when the runtime detects TweakerExecuteException being thrown and
	 * translates it to a TweakerException. Adds file and line information to
	 * the exception and rethrows it, with that exception usually being
	 * displayed somewhere.
	 * 
	 * @param file
	 * @param line
	 * @param offset
	 * @return the translated TweakerException
	 */
	public TweakerException handle(TweakerFile file, int line, int offset) {
		return new TweakerException(file, line, offset, getMessage());
	}
}
