/*
 * This file is subject to the license.txt file in the main folder
 * of this project.
 */

package stanhebben.minetweaker.script.parser;

/**
 * Represents a token in a token stream.
 *
 * @author Stan Hebben
 */
public class Token {
	private String file;
    private int line;
    private int lineOffset;
    private String value;
    private int type;

    /**
     * Constructs a new token.
     *
     * @param value token string value
     * @param type token type
     * @param line line number
     * @param lineOffset offset in line
     */
    public Token(String value, int type, String file, int line, int lineOffset) {
    	this.file = file;
        this.value = value;
        this.type = type;
        this.line = line;
        this.lineOffset = lineOffset;
    }
    
    public String getFile() {
    	return file;
    }

    /**
     * Returns the string value of this token.
     *
     * @return token value
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the token type of this token.
     *
     * @return token type
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the line number of this token. The first line in a file has line
     * number 1.
     *
     * @return line number
     */
    public int getLine() {
        return line;
    }

    /**
     * Returns the line offset of this token. The line offset is the character
     * offset, starting from the beginning of this line. The first character of
     * a line has number 1.
     *
     * @return line offset
     */
    public int getLineOffset() {
        return lineOffset;
    }

    @Override
    public String toString() {
        return line + ":" + lineOffset + " (" + type + ") " + value;
    }
}
