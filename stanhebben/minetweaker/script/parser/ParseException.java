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
	private Token token;
	private String message;
	
    public ParseException(Token token, String error) {
        super("Error parsing line " + token.getLine() + ":" + token.getLineOffset() + " - " + error + " (last token: " + token.getValue() + ")");
        
        this.token = token;
        this.message = error;
    }
    
    public String getFile() {
    	return token.getFile();
    }
    
    public int getLine() {
    	return token.getLine();
    }
    
    public int getLineOffset() {
    	return token.getLineOffset();
    }
    
    public String getExplanation() {
    	return message;
    }
}
