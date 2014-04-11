/*
 * This file is subject to the license.txt file in the main folder
 * of this project.
 */

package stanhebben.minetweaker.script.parser;

/**
 *
 * @author Stan
 */
public class ParseException extends RuntimeException {
	private final Token token;
	private final String message;
	
    public ParseException(Token token, String error) {
        super(token == null ? "Error at end of file - " + error : "Error parsing line " + token.getLine() + ":" + token.getLineOffset() + " - " + error + " (last token: " + token.getValue() + ")");
        
        this.token = token;
        this.message = error;
    }
    
    public String getFile() {
    	return token == null ? null : token.getFile();
    }
    
    public int getLine() {
    	return token == null ? -1 : token.getLine();
    }
    
    public int getLineOffset() {
    	return token == null ? -1 : token.getLineOffset();
    }
    
    public String getExplanation() {
    	return message;
    }
}
