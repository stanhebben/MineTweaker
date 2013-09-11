package stanhebben.minetweaker.api;

import stanhebben.minetweaker.script.TweakerFile;

/**
 * TweakerExceptions can be throws upon code execution. They contain information
 * about the source of the exception, the file and the location of the code
 * that caused the exception.
 * 
 * @author Stan Hebben
 */
public class TweakerException extends RuntimeException {
	private TweakerFile file;
	private int line;
	private int offset;
	private String explanation;
	
	public TweakerException(TweakerFile file, int line, int offset, String explanation) {
		super(file + " line " + line + ":" + offset + " - " + explanation);
		
		this.file = file;
		this.line = line;
		this.offset = offset;
		this.explanation = explanation;
	}
	
	public TweakerFile getFile() {
		return file;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public String getExplanation() {
		return explanation;
	}
}
