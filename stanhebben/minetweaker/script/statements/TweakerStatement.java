package stanhebben.minetweaker.script.statements;

import java.util.ArrayList;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.TweakerParser;
import stanhebben.minetweaker.script.expressions.TweakerExpression;
import stanhebben.minetweaker.script.parser.Token;
import static stanhebben.minetweaker.script.TweakerParser.*;

public abstract class TweakerStatement {
	public static TweakerStatement read(TweakerFile file, TweakerParser parser) {
		Token next = parser.peek();
		switch (next.getType()) {
			case T_AOPEN: {
				Token t = parser.next();
				ArrayList<TweakerStatement> statements = new ArrayList<TweakerStatement>();
				while (parser.optional(T_ACLOSE) == null) {
					statements.add(read(file, parser));
				}
				return new StatementBlock(file, t.getLine(), t.getLineOffset(), statements);
			}
			case T_RETURN: {
				parser.next();
				TweakerExpression expression = TweakerExpression.read(file, parser);
				parser.required(T_SEMICOLON, "; expected");
				return new StatementReturn(file, next.getLine(), next.getLineOffset(), expression);
			}
			case T_IF: {
				Token t = parser.next();
				TweakerExpression expression = TweakerExpression.read(file, parser);
				TweakerStatement onIf = read(file, parser);
				TweakerStatement onElse = null;
				if (parser.optional(T_ELSE) != null) {
					onElse = read(file, parser);
				}
				return new StatementIf(file, t.getLine(), t.getLineOffset(), expression, onIf, onElse);
			}
			case T_FOR: {
				Token t = parser.next();
				String name = parser.required(T_ID, "identifier expected").getValue();
				parser.required(T_COLON, ": expected");
				TweakerExpression source = TweakerExpression.read(file, parser);
				TweakerStatement content = read(file, parser);
				return new StatementForeach(file, t.getLine(), t.getLineOffset(), name, source, content);
			}
			case T_VERSION: {
				Token t = parser.next();
				int value = Integer.parseInt(parser.required(T_INTVALUE, "integer expected").getValue());
				// throws a TransformationException on version 1
				StatementVersion result = new StatementVersion(file, t.getLine(), t.getLineOffset(), value);
				parser.required(T_SEMICOLON, "; expected");
				return result;
			}
			case T_INCLUDE: {
				Token t = parser.next();
				TweakerExpression expression = TweakerExpression.read(file, parser);
				parser.required(T_SEMICOLON, "; expected");
				return new StatementInclude(file, t.getLine(), t.getLineOffset(), expression);
			}
		}
		
		int line = parser.peek().getLine();
		int offset = parser.peek().getLineOffset();
		TweakerExpression expression = TweakerExpression.read(file, parser);
		parser.required(T_SEMICOLON, "; expected");
		return new StatementExpression(file, line, offset, expression);
	}
	
	private TweakerFile file;
	private int line;
	private int offset;
	
	public TweakerStatement(TweakerFile file, int line, int offset) {
		this.file = file;
		this.line = line;
		this.offset = offset;
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
	
	public abstract TweakerValue execute(TweakerNameSpace namespace) throws TweakerException;
}
