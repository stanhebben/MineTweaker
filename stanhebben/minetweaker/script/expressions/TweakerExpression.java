package stanhebben.minetweaker.script.expressions;

import java.util.ArrayList;

import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerDouble;
import stanhebben.minetweaker.api.value.TweakerFunctionScript;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPatternAny;
import stanhebben.minetweaker.api.value.TweakerItemPatternAnyMeta;
import stanhebben.minetweaker.api.value.TweakerString;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.TweakerParser;
import stanhebben.minetweaker.script.parser.ParseException;
import stanhebben.minetweaker.script.parser.Token;
import stanhebben.minetweaker.script.statements.TweakerStatement;
import stanhebben.minetweaker.script.types.TweakerType;

public abstract class TweakerExpression {
	public static TweakerExpression read(TweakerFile file, TweakerParser parser) {
		return readAssignExpression(file, parser);
	}
	
	private static TweakerExpression readAssignExpression(TweakerFile file, TweakerParser parser) {
		int line = parser.peek().getLine();
		int offset = parser.peek().getLineOffset();
		
		TweakerExpression left = readConditionalExpression(file, line, offset, parser);
		switch (parser.peek() == null ? -1 : parser.peek().getType()) {
			case TweakerParser.T_ASSIGN:
				parser.next();
				return left.assign(file, line, offset, readAssignExpression(file, parser));
			case TweakerParser.T_PLUSASSIGN:
				parser.next();
				return new TweakerExpressionAddAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_MINUSASSIGN:
				parser.next();
				return new TweakerExpressionSubAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_MULASSIGN:
				parser.next();
				return new TweakerExpressionMulAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_DIVASSIGN:
				parser.next();
				return new TweakerExpressionDivAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_MODASSIGN:
				parser.next();
				return new TweakerExpressionModAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_ORASSIGN:
				parser.next();
				return new TweakerExpressionOrAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_ANDASSIGN:
				parser.next();
				return new TweakerExpressionAndAssign(file, line, offset, left, readAssignExpression(file, parser));
			case TweakerParser.T_XORASSIGN:
				parser.next();
				return new TweakerExpressionXorAssign(file, line, offset, left, readAssignExpression(file, parser));
		}
		
		return left;
	}
	
	private static TweakerExpression readConditionalExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readOrOrExpression(file, line, offset, parser);
		
		if (parser.optional(TweakerParser.T_QUEST) != null) {
			TweakerExpression onIf = readOrOrExpression(
					file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			parser.required(TweakerParser.T_COLON, ": expected");
			TweakerExpression onElse = readConditionalExpression(
					file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			return new TweakerExpressionConditional(file, line, offset, left, onIf, onElse);
		}
		
		return left;
	}
	
	private static TweakerExpression readOrOrExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readAndAndExpression(file, line, offset, parser);
		
		while (parser.optional(TweakerParser.T_OR2) != null) {
			TweakerExpression right = readAndAndExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			left = new TweakerExpressionOrOr(file, line, offset, left, right);
		}
		return left;
	}
	
	private static TweakerExpression readAndAndExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readOrExpression(file, line, offset, parser);
		
		while (parser.optional(TweakerParser.T_AND2) != null) {
			TweakerExpression right = readOrExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			left = new TweakerExpressionAndAnd(file, line, offset, left, right);
		}
		return left;
	}
	
	private static TweakerExpression readOrExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readXorExpression(file, line, offset, parser);
		
		while (parser.optional(TweakerParser.T_OR) != null) {
			TweakerExpression right = readXorExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			left = new TweakerExpressionOr(file, line, offset, left, right);
		}
		return left;
	}
	
	private static TweakerExpression readXorExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readAndExpression(file, line, offset, parser);
		
		while (parser.optional(TweakerParser.T_XOR) != null) {
			TweakerExpression right = readAndExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			left = new TweakerExpressionXor(file, line, offset, left, right);
		}
		return left;
	}
	
	private static TweakerExpression readAndExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readCompareExpression(file, line, offset, parser);
		
		while (parser.optional(TweakerParser.T_AND) != null) {
			TweakerExpression right = readCompareExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
			left = new TweakerExpressionAnd(file, line, offset, left, right);
		}
		return left;
	}
	
	private static TweakerExpression readCompareExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readAddExpression(file, line, offset, parser);
		
		switch (parser.peek() == null ? -1 : parser.peek().getType()) {
			case TweakerParser.T_EQ: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionEq(file, line, offset, left, right);
			}
			case TweakerParser.T_NOTEQ: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionNotEq(file, line, offset, left, right);
			}
			case TweakerParser.T_LT: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionLt(file, line, offset, left, right);
			}
			case TweakerParser.T_LTEQ: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionLtEq(file, line, offset, left, right);
			}
			case TweakerParser.T_GT: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionLt(file, line, offset, right, left);
			}
			case TweakerParser.T_GTEQ: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionLtEq(file, line, offset, right, left);
			}
			case TweakerParser.T_IN: {
				parser.next();
				TweakerExpression right = readAddExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				return new TweakerExpressionIn(file, line, offset, right, left);
			}
		}
		
		return left;
	}
	
	private static TweakerExpression readAddExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readMulExpression(file, line, offset, parser);
		
		while (true) {
			if (parser.optional(TweakerParser.T_PLUS) != null) {
				TweakerExpression right = readMulExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				left = new TweakerExpressionAdd(file, line, offset, left, right);
			} else if (parser.optional(TweakerParser.T_MINUS) != null) {
				TweakerExpression right = readMulExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				left = new TweakerExpressionSub(file, line, offset, left, right);
			} else {
				break;
			}
		}
		return left;
	}
	
	private static TweakerExpression readMulExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression left = readUnaryExpression(file, line, offset, parser);
		
		while (true) {
			if (parser.optional(TweakerParser.T_MUL) != null) {
				TweakerExpression right = readUnaryExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				left = new TweakerExpressionMul(file, line, offset, left, right);
			} else if (parser.optional(TweakerParser.T_DIV) != null) {
				TweakerExpression right = readUnaryExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				left = new TweakerExpressionDiv(file, line, offset, left, right);
			} else if (parser.optional(TweakerParser.T_MOD) != null) {
				TweakerExpression right = readUnaryExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
				left = new TweakerExpressionMod(file, line, offset, left, right);
			} else {
				break;
			}
		}
		
		return left;
	}
	
	private static TweakerExpression readUnaryExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		switch (parser.peek().getType()) {
			case TweakerParser.T_NOT:
				parser.next();
				return new TweakerExpressionNot(file, line, offset,
						readUnaryExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser));
			case TweakerParser.T_MINUS:
				parser.next();
				return new TweakerExpressionNeg(file, line, offset,
						readUnaryExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser));
			default:
				return readPostfixExpression(file, line, offset, parser);
		}
	}
	
	private static TweakerExpression readPostfixExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerExpression base = readPrimaryExpression(file, line, offset, parser);
		
		while (true) {
			if (parser.optional(TweakerParser.T_DOT) != null) {
				Token indexString = parser.optional(TweakerParser.T_ID);
				if (indexString != null) {
					base = new TweakerExpressionIndexString(file, line, offset, base, indexString.getValue());
				} else {
					TweakerExpression index = readPrimaryExpression(file, parser.peek().getLine(), parser.peek().getLineOffset(), parser);
					base = new TweakerExpressionIndex(file, line, offset, base, index);
				}
			} else if (parser.optional(TweakerParser.T_DOT2) != null) {
				TweakerExpression to = readAssignExpression(file, parser);
				base = new TweakerExpressionRange(file, line, offset, base, to);
			} else if (parser.optional(TweakerParser.T_SQBROPEN) != null) {
				TweakerExpression index = readAssignExpression(file, parser);
				base = new TweakerExpressionIndex(file, line, offset, base, index);
				parser.required(TweakerParser.T_SQBRCLOSE, "] expected");
			} else if (parser.optional(TweakerParser.T_BROPEN) != null) {
				if (parser.optional(TweakerParser.T_BRCLOSE) != null) {
					base = new TweakerExpressionCall(file, line, offset, base, new TweakerExpression[0]);
				} else {
					ArrayList<TweakerExpression> arguments = new ArrayList<TweakerExpression>();
					arguments.add(readAssignExpression(file, parser));
					while (parser.optional(TweakerParser.T_COMMA) != null) {
						arguments.add(readAssignExpression(file, parser));
					}
					base = new TweakerExpressionCall(file, line, offset, base, arguments.toArray(new TweakerExpression[arguments.size()]));
					parser.required(TweakerParser.T_BRCLOSE, ") expected");
				}
			} else if (parser.optional(TweakerParser.T_AS) != null) {
				TweakerType type = TweakerType.read(file, line, offset, parser);
				base = new TweakerExpressionAs(file, line, offset, base, type);
			} else {
				break;
			}
		}
		
		return base;
	}
	
	private static TweakerExpression readPrimaryExpression(TweakerFile file, int line, int offset, TweakerParser parser) {
		switch (parser.peek().getType()) {
			case TweakerParser.T_INTVALUE:
				return new TweakerExpressionConstant(file, line, offset, new TweakerInt(Integer.parseInt(parser.next().getValue())));
			case TweakerParser.T_FLOATVALUE:
				return new TweakerExpressionConstant(file, line, offset, new TweakerDouble(Double.parseDouble(parser.next().getValue())));
			case TweakerParser.T_STRINGVALUE:
				try {
					return new TweakerExpressionConstant(file, line, offset, new TweakerString(TweakerString.unescapeString(parser.next().getValue())));
				} catch (TweakerExecuteException ex) {
					throw new RuntimeException(ex.getMessage());
				}
			case TweakerParser.T_ID:
				return new TweakerExpressionVariable(file, line, offset, parser.next().getValue());
			case TweakerParser.T_FUNCTION:
				// function (argname, argname, ...) { ...contents... }
				parser.next();
				parser.required(TweakerParser.T_BROPEN, "( expected");
				
				String[] argumentNames;
				if (parser.optional(TweakerParser.T_BRCLOSE) != null) {
					argumentNames = new String[0];
				} else {
					ArrayList<String> argumentNamesAL = new ArrayList<String>();
					argumentNamesAL.add(parser.required(TweakerParser.T_ID, "identifier expected").getValue());
					
					while (parser.optional(TweakerParser.T_COMMA) != null) {
						argumentNamesAL.add(parser.required(TweakerParser.T_ID, "identifier expected").getValue());
					}
					argumentNames = argumentNamesAL.toArray(new String[argumentNamesAL.size()]);
					parser.required(TweakerParser.T_BRCLOSE, ") expected");
				}
				
				parser.required(TweakerParser.T_AOPEN, "{ expected");
				
				TweakerStatement[] statements;
				if (parser.optional(TweakerParser.T_ACLOSE) != null) {
					statements = new TweakerStatement[0];
				} else {
					ArrayList<TweakerStatement> statementsAL = new ArrayList<TweakerStatement>();
					
					while (parser.optional(TweakerParser.T_ACLOSE) == null) {
						statementsAL.add(TweakerStatement.read(file, parser));
					}
					statements = statementsAL.toArray(new TweakerStatement[statementsAL.size()]);
				}
				return new TweakerExpressionConstant(file, line, offset, new TweakerFunctionScript(argumentNames, statements));
			case TweakerParser.T_LT:
				parser.next();
				if (parser.optional(TweakerParser.T_MUL) != null) {
					parser.required(TweakerParser.T_GT, "> expected");
					return new TweakerExpressionConstant(file, line, offset, TweakerItemPatternAny.INSTANCE);
				} else {
					int id = Integer.parseInt(parser.required(TweakerParser.T_INTVALUE, "integer expected").getValue());
					if (parser.optional(TweakerParser.T_COLON) != null) {
						if (parser.optional(TweakerParser.T_MUL) != null) {
							parser.required(TweakerParser.T_GT, "> expected");
							return new TweakerExpressionConstant(file, line, offset, new TweakerItemPatternAnyMeta(id));
						} else {
							int meta = Integer.parseInt(parser.required(TweakerParser.T_INTVALUE, "integer expected").getValue());
							parser.required(TweakerParser.T_GT, "> expected");
							return new TweakerExpressionItemIdMeta(file, line, offset, id, meta);
						}
					} else {
						parser.required(TweakerParser.T_GT, "> expected");
						return new TweakerExpressionItemId(file, line, offset, id);
					}
				}
			case TweakerParser.T_SQBROPEN: {
				parser.next();
				if (parser.optional(TweakerParser.T_SQBRCLOSE) != null) {
					return new TweakerExpressionArray(file, line, offset, new TweakerExpression[0]);
				} else {
					ArrayList<TweakerExpression> contents = new ArrayList<TweakerExpression>();
					while (parser.optional(TweakerParser.T_SQBRCLOSE) == null) {
						contents.add(readAssignExpression(file, parser));
						if (parser.optional(TweakerParser.T_COMMA) == null) {
							parser.required(TweakerParser.T_SQBRCLOSE, "] or , expected");
							break;
						}
					}
					return new TweakerExpressionArray(file, line, offset, contents.toArray(new TweakerExpression[contents.size()]));
				}
			}
			case TweakerParser.T_AOPEN: {
				parser.next();
				if (parser.optional(TweakerParser.T_ACLOSE) != null) {
					return new TweakerExpressionTable(file, line, offset, new TweakerExpression[0], new TweakerExpression[0]);
				} else {
					ArrayList<TweakerExpression> keys = new ArrayList<TweakerExpression>();
					ArrayList<TweakerExpression> values = new ArrayList<TweakerExpression>();
					
					while (parser.optional(TweakerParser.T_ACLOSE) == null) {
						keys.add(readAssignExpression(file, parser));
						parser.required(TweakerParser.T_COLON, ": expected");
						values.add(readAssignExpression(file, parser));
						
						if (parser.optional(TweakerParser.T_COMMA) == null) {
							parser.required(TweakerParser.T_ACLOSE, "} or , expected");
							break;
						}
					}
					
					return new TweakerExpressionTable(file, line, offset,
							keys.toArray(new TweakerExpression[keys.size()]),
							values.toArray(new TweakerExpression[values.size()]));
				}
			}
			case TweakerParser.T_TRUE:
				parser.next();
				return new TweakerExpressionConstant(file, line, offset, TweakerBool.TRUE);
			case TweakerParser.T_FALSE:
				parser.next();
				return new TweakerExpressionConstant(file, line, offset, TweakerBool.FALSE);
			case TweakerParser.T_NULL:
				parser.next();
				return new TweakerExpressionConstant(file, line, offset, null);
			case TweakerParser.T_BROPEN:
				parser.next();
				TweakerExpression result = readAssignExpression(file, parser);
				parser.required(TweakerParser.T_BRCLOSE, ") expected");
				return result;
			default:
				Token last = parser.next();
				throw new ParseException(last, "Invalid expression, last token: " + last.getValue());
		}
	}
	
	private final TweakerFile file;
	private final int line;
	private final int offset;
	
	public TweakerExpression(TweakerFile file, int line, int offset) {
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
	
	public TweakerValue execute(TweakerNameSpace namespace) {
		try {
			return executeInner(namespace);
		} catch (TweakerExecuteException ex) {
			throw new TweakerException(file, line, offset, ex.getMessage());
		}
	}
	
	public abstract TweakerValue executeInner(TweakerNameSpace namespace);
	
	public TweakerExpression assign(TweakerFile file, int line, int offset, TweakerExpression src) {
		return new TweakerExpressionAssign(file, line, offset, this, src);
	}
	
	public void assign(TweakerNameSpace namespace, TweakerValue value) {
		throw new TweakerException(file, line, offset, "Not an lvalue");
	}
}
