package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.script.TweakerFile;
import stanhebben.minetweaker.script.TweakerParser;
import stanhebben.minetweaker.script.parser.ParseException;
import stanhebben.minetweaker.script.parser.Token;

public abstract class TweakerType {
	public static TweakerType read(TweakerFile file, int line, int offset, TweakerParser parser) {
		TweakerType base;
		
		Token next = parser.next();
		switch (next.getType()) {
		case TweakerParser.T_BOOL:
			base = TweakerTypeBool.INSTANCE;
			break;
		case TweakerParser.T_BYTE:
			base = TweakerTypeByte.INSTANCE;
			break;
		case TweakerParser.T_SHORT:
			base = TweakerTypeShort.INSTANCE;
			break;
		case TweakerParser.T_INT:
			base = TweakerTypeInt.INSTANCE;
			break;
		case TweakerParser.T_LONG:
			base = TweakerTypeLong.INSTANCE;
			break;
		case TweakerParser.T_FLOAT:
			base = TweakerTypeFloat.INSTANCE;
			break;
		case TweakerParser.T_DOUBLE:
			base = TweakerTypeDouble.INSTANCE;
			break;
		case TweakerParser.T_STRING:
			base = TweakerTypeString.INSTANCE;
			break;
		default:
			throw new ParseException(next, "Unknown type: " + next.getValue());
		}
		
		while (parser.optional(TweakerParser.T_SQBROPEN) != null) {
			parser.required(TweakerParser.T_SQBRCLOSE, "] expected");
			base = new TweakerTypeArray(base);
		}
		
		return base;
	}
	
	public abstract TweakerValue as(TweakerValue original);
}
